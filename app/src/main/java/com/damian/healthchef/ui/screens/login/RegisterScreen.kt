package com.damian.healthchef.ui.screens.login

import android.graphics.Color
import androidx.compose.material3.AlertDialog
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.damian.healthchef.R
import com.damian.healthchef.ui.components.ButtonLoginRegister
import com.damian.healthchef.ui.components.EmailInput
import com.damian.healthchef.ui.components.PasswordInput
import com.damian.healthchef.ui.components.RepeatPasswordInput
import com.damian.healthchef.ui.components.UsernameInput
import kotlinx.coroutines.delay

@Composable
fun RegisterScreen(
    onContinueLogin: () -> Unit,
    onRegisterSuccess: () -> Unit,
    onError: (String) -> Unit = {}
) {
    val username = rememberSaveable { mutableStateOf("") }
    val email = rememberSaveable { mutableStateOf("") }
    val password = rememberSaveable { mutableStateOf("") }
    val repeatPassword = rememberSaveable { mutableStateOf("") }
    val passwordVisible = rememberSaveable { mutableStateOf(false) }

    val showErrorDialog = remember { mutableStateOf(false) }

    val keyboardController = LocalSoftwareKeyboardController.current

    val valido by remember(email.value, password.value) {
        derivedStateOf {
            email.value.trim().isNotEmpty() && password.value.trim().isNotEmpty()
        }
    }

    LaunchedEffect(showErrorDialog.value) {
        if (showErrorDialog.value) {
            onError("Las contraseñas no coinciden")
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.health_chef_logo),
            contentDescription = "Logo",
            modifier = Modifier.size(300.dp)
        )

        /*Inputs Register*/
        UsernameInput(usernameState = username)
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

        Spacer(modifier = Modifier.height(16.dp))

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

        Spacer(modifier = Modifier.height(16.dp))

        ButtonLoginRegister(
            textId = "Regístrate",
            inputValido = valido
        ) {
            if (password.value.trim() == repeatPassword.value.trim()) {
                onRegisterSuccess()
                keyboardController?.hide()
            } else {
                onError("Las contraseñas no coinciden")
                showErrorDialog.value = true
            }
        }
        if (showErrorDialog.value) {
            ErrorDialog(
                errorMessage = "Las contraseñas no coinciden",
                onDismiss = { showErrorDialog.value = false }
            )
        }

    }
}

@Composable
fun ErrorDialog(
    errorMessage: String,
    onDismiss: () -> Unit,
    dialogWidth: Dp = 1000.dp
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(
            text = "Error",
            fontSize = 26.sp,
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
        ) },
        text = {
            Text(
                text = errorMessage,
                modifier = Modifier.fillMaxWidth(),
                fontSize = 18.sp
            )
        },
        confirmButton = {
            Button(
                onClick = onDismiss,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    "OK",
                    color = MaterialTheme.colorScheme.background,
                    fontSize = 18.sp

                )
            }
        },
        modifier = Modifier.fillMaxWidth()
    )
}
