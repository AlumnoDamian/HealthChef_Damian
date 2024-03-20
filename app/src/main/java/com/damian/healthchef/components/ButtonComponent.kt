package com.damian.healthchef.components

import android.app.AlertDialog
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun ButtonLoginRegister(
    textId: String,
    inputValido: Boolean,
    onClick: () -> Unit
) {

    val color = if (inputValido) {
        MaterialTheme.colorScheme.primary
    } else {
        MaterialTheme.colorScheme.secondary
    }.copy(alpha = 0.5f)

    Button(
        onClick = onClick,
        modifier = Modifier
            .padding(5.dp)
            .background(color, shape = CircleShape)
            .width(300.dp),
        enabled = inputValido
    ) {
        Text(
            text = textId,
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier.padding(5.dp)
        )
    }
}

@Composable
fun ButtonLogOut(onLogOut: () -> Unit){
    val context = LocalContext.current
    Button(
        onClick = {
            AlertDialog.Builder(context)
                .setTitle("Cerrar sesión")
                .setMessage("¿Estás seguro de que quieres cerrar sesión?")
                .setPositiveButton("Sí") { dialog, _ ->
                    onLogOut()
                    dialog.dismiss()
                }
                .setNegativeButton("Cancelar") { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        },
        modifier = Modifier
            .padding(10.dp)
            .width(300.dp),
        shape = CircleShape,
    ) {
        Text(text = "Cerrar sesión")
    }
}