package com.damian.healthchef.ui.components.inputs

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.damian.healthchef.ui.components.inputs.InputField

@Composable
fun EmailInput(
    emailState: MutableState<String>,
    labelId: String = "Email"
) {
    InputField(
        valueState = emailState,
        labelId = labelId,
        keyboardType = KeyboardType.Email
    )
}

@Composable
fun PasswordInput(
    passwordState: MutableState<String>,
    labelId: String = "Contraseña",
    passwordVisible: Boolean,
    onToggleClick: () -> Unit
) {
    InputField(
        valueState = passwordState,
        labelId = labelId,
        keyboardType = KeyboardType.Password,
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = Icons.Outlined.Visibility,
        onIconClick = onToggleClick
    )
}

@Composable
fun RepeatPasswordInput(
    passwordState: MutableState<String>,
    labelId: String = "Repetir contraseña",
    passwordVisible: Boolean,
    onToggleClick: () -> Unit
) {
    InputField(
        valueState = passwordState,
        labelId = labelId,
        keyboardType = KeyboardType.Password,
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = Icons.Outlined.Visibility,
        onIconClick = onToggleClick
    )
}