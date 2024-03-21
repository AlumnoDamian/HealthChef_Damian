package com.damian.healthchef.ui.components

import android.app.AlertDialog
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CommentBank
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Send
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
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
        modifier = Modifier.padding(10.dp).width(300.dp),
        shape = CircleShape,
    ) {
        Text(
            text = "Cerrar sesión",
            color = MaterialTheme.colorScheme.background,
        )
    }
}

@Composable
fun ButtonIcons(
    initialValueFavorite: Int,
    initialValueComment: Int,
    initialValueSend: Int,
    onFavoriteClick: () -> Unit,
    onCommentClick: () -> Unit,
    onSendClick: () -> Unit
) {
    var favoriteCount by remember { mutableStateOf(initialValueFavorite) }
    var commentCount by remember { mutableStateOf(initialValueComment) }
    var sendCount by remember { mutableStateOf(initialValueSend) }

    Row(
        modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(16.dp)),
    horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row {
        }
        ButtonIconWithCounter(
            icon = Icons.Outlined.Favorite,
            count = favoriteCount,
            onClick = {
                if (favoriteCount > initialValueFavorite) {
                    favoriteCount--
                } else {
                    favoriteCount++
                }
                onFavoriteClick()
            },
            modifier = Modifier.weight(2f)
        )

        ButtonIconWithCounter(
            icon = Icons.Outlined.CommentBank,
            count = commentCount,
            onClick = {
                if (commentCount > initialValueComment) {
                    commentCount--
                } else {
                    commentCount++
                }
                onCommentClick()
            },
            modifier = Modifier.weight(2f)
        )

        ButtonIconWithCounter(
            icon = Icons.Outlined.Send,
            count = sendCount,
            onClick = {
                if (sendCount > initialValueSend) {
                    sendCount--
                } else {
                    sendCount++
                }
                onSendClick()
            },
            modifier = Modifier.weight(1f)
        )
    }
}


@Composable
fun ButtonIconWithCounter(
    icon: ImageVector,
    count: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.primary),
        verticalAlignment = Alignment.CenterVertically
    ){
        IconButton(
            onClick = onClick,
            modifier = Modifier
                .padding(end = 4.dp)
        ) {
            Icon(imageVector = icon, contentDescription = null)
        }
        Text(
            text = "$count",
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier.padding(end = 8.dp)
        )
    }
}