package com.damian.healthchef.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun ErrorDialog(
    errorMessage: String,
    onDismiss: () -> Unit
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