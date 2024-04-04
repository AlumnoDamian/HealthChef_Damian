package com.damian.healthchef.ui.components.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AddRecipeButton(
    onClick: () -> Unit,
    enabled: Boolean,
) {
    val color = if (enabled) {
        MaterialTheme.colorScheme.primary
    } else {
        MaterialTheme.colorScheme.secondary
    }.copy(alpha = 0.5f)

    Button(
        onClick = onClick,
        modifier = Modifier
            .padding(4.dp)
            .background(color, shape = CircleShape)
            .width(300.dp),
        enabled = enabled
    ) {
        Text(
            text = "Agregar",
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier.padding(4.dp),
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )
    }
}