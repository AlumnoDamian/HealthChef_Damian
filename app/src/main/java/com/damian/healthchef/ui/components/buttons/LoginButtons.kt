package com.damian.healthchef.ui.components.buttons

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
            .padding(4.dp)
            .background(color, shape = CircleShape)
            .width(300.dp),
        enabled = inputValido
    ) {
        Text(
            text = textId,
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier.padding(4.dp),
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
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
            .fillMaxWidth(),
        shape = CircleShape,
    ) {
        Text(
            text = "Cerrar sesión",
            color = MaterialTheme.colorScheme.background,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )
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
            .padding(start = 36.dp, end = 36.dp)
            .clip(RoundedCornerShape(24.dp))
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
            modifier = Modifier.padding(10.dp).size(40.dp)
        )
        Text(
            text = "Login con Google",
            color = MaterialTheme.colorScheme.background,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
    }
}