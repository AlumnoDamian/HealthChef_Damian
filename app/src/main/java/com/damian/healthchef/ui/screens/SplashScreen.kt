package com.damian.healthchef.ui.screens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.damian.healthchef.R
import com.damian.healthchef.ui.navigation.Screens
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.delay

@ExperimentalFoundationApi
@Composable
fun SplashScreen(navController: NavController) {
    // Valor animado para la opacidad
    val alpha by animateFloatAsState(
        targetValue = 1f,
        animationSpec = tween(
            durationMillis = 1000,
            delayMillis = 500
        )
    )

    // Efecto lanzado cuando la pantalla se muestra
    LaunchedEffect(key1 = true) {
        delay(2500L)
        navController.popBackStack()
        // Determina a qué pantalla navegar según el estado del usuario
        if (FirebaseAuth.getInstance().currentUser?.email.isNullOrEmpty()){
            navController.navigate(Screens.BottomBarScreens.Login.route)
        } else {
            navController.navigate(Screens.BottomBarScreens.Recipe.route)
        }
    }

    // Diseño de la pantalla de presentación
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.alpha(alpha),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Logo de la aplicación
            Image(
                painter = painterResource(id = R.drawable.health_chef_logo),
                contentDescription = null,
                modifier = Modifier.size(300.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Créditos
            Text(
                text = "Hecho por Damián Madueño Bolaños",
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 20.sp),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}