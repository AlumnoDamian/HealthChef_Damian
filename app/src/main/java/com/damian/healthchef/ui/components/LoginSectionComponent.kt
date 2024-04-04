@file:OptIn(ExperimentalComposeUiApi::class)

package com.damian.healthchef.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import com.damian.healthchef.ui.components.inputs.EmailInput
import com.damian.healthchef.ui.components.inputs.PasswordInput
import com.damian.healthchef.ui.components.inputs.RepeatPasswordInput
import com.damian.healthchef.viewmodel.login.RegisterViewModel
import com.damian.healthchef.viewmodel.login.SignInViewModel

@Composable
fun SignInSection(
    viewModel: SignInViewModel,
    onLoginSuccess: (String, String) -> Unit = { email, password -> },
    onContinueRegister: () -> Unit,
    onError: (String) -> Unit = {}
) {
    val email = rememberSaveable { mutableStateOf("") }
    val password = rememberSaveable { mutableStateOf("") }
    val passwordVisible = rememberSaveable { mutableStateOf(false) }
    var showError by remember { mutableStateOf(false) }

    val keyboardController = LocalSoftwareKeyboardController.current

    val valido = remember(email.value, password.value) {
        email.value.trim().isNotEmpty() && password.value.trim().isNotEmpty()
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        EmailInput(emailState = email)
        PasswordInput(
            passwordState = password,
            passwordVisible = passwordVisible.value,
            onToggleClick = { passwordVisible.value = !passwordVisible.value }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "¿No tienes cuenta?",
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = "Regístrate",
                modifier = Modifier
                    .clickable(onClick = onContinueRegister)
                    .padding(start = 5.dp),
                color = MaterialTheme.colorScheme.secondary
            )
        }

        ButtonLoginRegister(
            textId = "Login",
            inputValido = valido
        ) {
            viewModel.signInWithEmailAndPassword(
                email.value.trim(),
                password.value.trim(),
                onLoginSuccess = {
                    onLoginSuccess(email.value.trim(), password.value.trim())
                },
                onError = {
                    showError = true // Mostrar el cuadro de diálogo de error
                }
            )
            keyboardController?.hide()
        }

        if (showError) {
            ErrorDialog(
                errorMessage = "Valores incorrectos",
                onDismiss = { showError = false }
            )
        }

    }
}

@Composable
fun RegisterSection(
    viewModel: RegisterViewModel,
    onRegisterSuccess: (String, String) -> Unit = {email, password -> },
    onContinueLogin: () -> Unit,
    onError: (String) -> Unit = {}
) {
    val email = rememberSaveable { mutableStateOf("") }
    val password = rememberSaveable { mutableStateOf("") }
    val repeatPassword = rememberSaveable { mutableStateOf("") }
    val passwordVisible = rememberSaveable { mutableStateOf(false) }

    val showErrorDialog = remember { mutableStateOf(false) }

    val keyboardController = LocalSoftwareKeyboardController.current

    val isValidEmail by remember(email.value) {
        mutableStateOf(isValidEmail(email.value.trim()))
    }

    LaunchedEffect(showErrorDialog.value) {
        if (showErrorDialog.value) {
            onError("")
        }
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        /*Inputs Register*/
        EmailInput(emailState = email)
        PasswordInput(
            passwordState = password,
            passwordVisible = passwordVisible.value,
            onToggleClick = { passwordVisible.value = !passwordVisible.value }
        )
        RepeatPasswordInput(
            passwordState = repeatPassword,
            passwordVisible = passwordVisible.value,
            onToggleClick = { passwordVisible.value = !passwordVisible.value }
        )
        /*Inputs Register*/

        Spacer(modifier = Modifier.height(15.dp))

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "¿Ya tienes cuenta?",
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = "Inicia sesión aquí",
                modifier = Modifier
                    .clickable(onClick = onContinueLogin)
                    .padding(start = 5.dp),
                color = MaterialTheme.colorScheme.secondary
            )
        }

        ButtonLoginRegister(
            textId = "Regístrate",
            inputValido = isValidEmail && password.value.trim().isNotEmpty() && repeatPassword.value.trim().isNotEmpty(),
            onClick = {
                if (password.value.trim() == repeatPassword.value.trim()) {
                    onRegisterSuccess(email.value.trim(), password.value.trim())
                    keyboardController?.hide()
                } else {
                    onError("Las contraseñas no coinciden")
                    showErrorDialog.value = true
                }
            }
        )
        if (showErrorDialog.value) {
            ErrorDialog(
                errorMessage = "Las contraseñas no coinciden",
                onDismiss = { showErrorDialog.value = false }
            )
        }

    }
}

fun isValidEmail(email: String): Boolean {
    val emailRegex = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})"
    return email.matches(emailRegex.toRegex())
}