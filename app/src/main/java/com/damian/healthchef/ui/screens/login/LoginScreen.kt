package com.damian.healthchef.ui.screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import com.damian.healthchef.ui.components.ButtonLoginRegister
import com.damian.healthchef.ui.components.EmailInput
import com.damian.healthchef.ui.components.PasswordInput

@Composable
fun LoginScreen(
    onContinueRegister: () -> Unit,
    onLoginSuccess: () -> Unit
) {

    val email = rememberSaveable { mutableStateOf("") }
    val password = rememberSaveable { mutableStateOf("") }
    val passwordVisible = rememberSaveable { mutableStateOf(false) }

    val keyboardController = LocalSoftwareKeyboardController.current

    val valido = remember(email.value, password.value) {
        email.value.trim().isNotEmpty() && password.value.trim().isNotEmpty()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.health_chef_logo),
            contentDescription = "Logo",
            modifier = Modifier.size(300.dp)
        )

        Text(text = "Iniciar sesión")

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

        Spacer(modifier = Modifier.height(16.dp))

        ButtonLoginRegister(
            textId = "Iniciar sesión",
            inputValido = valido
        ) {
            onLoginSuccess()
            keyboardController?.hide()
        }
    }
}

