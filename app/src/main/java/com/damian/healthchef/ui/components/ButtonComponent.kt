package com.damian.healthchef.ui.components

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.result.ActivityResult
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.outlined.ChatBubble
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.damian.healthchef.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions

@Composable
fun ButtonLoginRegister(
    textId: String,
    inputValido: Boolean,
    onClick: () -> Unit,
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
        modifier = Modifier.fillMaxWidth().padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // Botones de la izquierda
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            ButtonWithSpace(
                onClick = {
                    if (favoriteCount > initialValueFavorite) {
                        favoriteCount--
                    } else {
                        favoriteCount++
                    }
                    onFavoriteClick()
                },
                icon = Icons.Outlined.Favorite,
                count = favoriteCount
            )

            Spacer(modifier = Modifier.width(8.dp))

            ButtonWithSpace(
                onClick = {
                    if (commentCount > initialValueComment) {
                        commentCount--
                    } else {
                        commentCount++
                    }
                    onCommentClick()
                },
                icon = Icons.Outlined.ChatBubble,
                count = commentCount
            )
        }

        // Botón de la derecha
        ButtonWithSpace(
            onClick = {
                if (sendCount > initialValueSend) {
                    sendCount--
                } else {
                    sendCount++
                }
                onSendClick()
            },
            icon = Icons.Filled.Send,
            count = sendCount
        )
    }
}

@Composable
fun ButtonWithSpace(
    onClick: () -> Unit,
    icon: ImageVector,
    count: Int,
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(MaterialTheme.colorScheme.primary)
            .clickable(onClick = onClick)
            .padding(8.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )

            Spacer(modifier = Modifier.width(4.dp))

            Text(
                text = "$count",
                color = MaterialTheme.colorScheme.background,
                fontSize = 18.sp,
                modifier = Modifier.padding(start = 4.dp, end = 8.dp)
            )
        }
    }
}

@Composable
fun GoogleLoginButton(
    context: Context,
    launcher: ManagedActivityResultLauncher<Intent, ActivityResult>
) {
    val token = "695596609890-sfqa9ebag4moqn54rros07veu3mrbhpi.apps.googleusercontent.com"
    val googleSignInOptions = remember(token) {
        GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(token)
            .requestEmail()
            .build()
    }
    val googleSignInClient = remember(context, googleSignInOptions) {
        GoogleSignIn.getClient(context, googleSignInOptions)
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clip(RoundedCornerShape(10.dp))
            .clickable {
                launcher.launch(googleSignInClient.signInIntent)
            }
            .background(Color.White),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.icon_google),
            contentDescription = "Login con Google",
            modifier = Modifier
                .padding(10.dp)
                .size(40.dp)
        )
        Text(
            text = "Login con Google",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
    }
}