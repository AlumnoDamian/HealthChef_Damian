@file:OptIn(ExperimentalMaterial3Api::class)

package com.damian.healthchef.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun InputField(
    valueState: MutableState<String>,
    modifier: Modifier = Modifier,
    labelId: String,
    trailingIcon: ImageVector? = null,
    onIconClick: (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardType: KeyboardType,
    isSingleLine: Boolean = true
) {
    OutlinedTextField(
        value = valueState.value,
        onValueChange = { valueState.value = it },
        modifier = modifier
            .padding(bottom = 10.dp, start = 10.dp, end = 10.dp)
            .fillMaxWidth(),
        label = { Text(labelId) },
        trailingIcon = {
            if (trailingIcon != null && onIconClick != null) {
                IconButton(
                    onClick = onIconClick,
                    content = {
                        Icon(
                            imageVector = trailingIcon,
                            contentDescription = null
                        )
                    }
                )
            }
        },
        visualTransformation = visualTransformation,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        singleLine = isSingleLine,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = MaterialTheme.colorScheme.secondary,
            unfocusedBorderColor = MaterialTheme.colorScheme.primary,
            focusedLabelColor = MaterialTheme.colorScheme.secondary,
            unfocusedLabelColor = MaterialTheme.colorScheme.primary,
            focusedTextColor = MaterialTheme.colorScheme.secondary,
            unfocusedTextColor = MaterialTheme.colorScheme.primary
        )
    )
}


@Composable
fun UsernameInput(
    usernameState: MutableState<String>,
    labelId: String = "Nombre de usuario"
) {
    InputField(
        valueState = usernameState,
        labelId = labelId,
        keyboardType = KeyboardType.Text,
        isSingleLine = true
    )
}

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