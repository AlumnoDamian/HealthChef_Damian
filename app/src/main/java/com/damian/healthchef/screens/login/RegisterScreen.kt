package com.damian.healthchef.screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.damian.healthchef.R
import com.damian.healthchef.components.ButtonLoginRegister
import com.damian.healthchef.components.EmailInput
import com.damian.healthchef.components.PasswordInput
import com.damian.healthchef.components.RepeatPasswordInput
import com.damian.healthchef.components.UsernameInput

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

    val keyboardController = LocalSoftwareKeyboardController.current

    val valido = remember(email.value, password.value) {
        email.value.trim().isNotEmpty() && password.value.trim().isNotEmpty()
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
            }
        }
    }
}

