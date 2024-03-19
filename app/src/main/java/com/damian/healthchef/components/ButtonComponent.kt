package com.damian.healthchef.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ButtonLoginRegister(
    textId: String,
    inputValido: Boolean,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .padding(5.dp)
            .width(300.dp),
        shape = CircleShape,
        enabled = inputValido
    ) {
        Text(
            text = textId,
            modifier = Modifier.padding(5.dp)
        )
    }
}