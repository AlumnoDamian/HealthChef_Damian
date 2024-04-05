package com.damian.healthchef.ui.screens

import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.damian.healthchef.ui.components.ErrorDialog
import com.damian.healthchef.ui.components.RegisterSection
import com.damian.healthchef.ui.components.SignInSection
import com.damian.healthchef.ui.components.buttons.GoogleLoginButton
import com.damian.healthchef.ui.navigation.Screens
import com.damian.healthchef.viewmodel.login.RegisterViewModel
import com.damian.healthchef.viewmodel.login.SignInViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider

@Composable
fun LoginScreen(
    navController: NavController,
    signInViewModel: SignInViewModel = viewModel(),
    registerViewModel: RegisterViewModel = viewModel(),
) {
    // Estado para mostrar el formulario de inicio de sesión o registro
    var showLoginForm by rememberSaveable { mutableStateOf(true) }

    // Contexto local
    val context = LocalContext.current

    // Estado para mostrar el diálogo de error
    val showErrorDialog = remember { mutableStateOf(false) }

    // Lanzador para el inicio de sesión con Google
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) {
        val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)
        try {
            val account = task.getResult(ApiException::class.java)
            val credential = GoogleAuthProvider.getCredential(account.idToken, null)
            signInViewModel.signInWithGoogleCredential(credential) {
                navController.navigate(Screens.BottomBarScreens.Recipe.route)
            }
        } catch (ex: Exception) {
            Log.d("HealthChef", "GoogleSignIn falló")
        }
    }

    // Diseño de la pantalla de inicio de sesión
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // Título dependiendo del estado de mostrar el formulario
            Text(
                text = if (showLoginForm) "Iniciar Sesión" else "Registrarse",
                fontSize = 24.sp,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(bottom = 100.dp)
            )

            // Mostrar formulario de inicio de sesión o registro
            if (showLoginForm) {
                SignInSection(
                    viewModel = signInViewModel,
                    onLoginSuccess =  { email, password ->
                        signInViewModel.signInWithEmailAndPassword(
                            email,
                            password,
                            onLoginSuccess = {
                                navController.navigate(Screens.BottomBarScreens.Recipe.route)
                            },
                            onError = {
                                showErrorDialog.value = true
                            }
                        )
                    },
                    onContinueRegister = { showLoginForm = false }
                )
            } else {
                RegisterSection(
                    viewModel = registerViewModel,
                    onRegisterSuccess = { email, password ->
                        registerViewModel.createUserWithEmailAndPassword(email, password) {
                            navController.navigate(Screens.BottomBarScreens.Login.route)
                        }
                    },
                    onContinueLogin = { showLoginForm = true },
                    onError = { showErrorDialog.value = true }
                )
            }

            // Separador
            Text(
                text = "----------------- o -----------------",
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(top = 24.dp, bottom = 24.dp)
            )

            // Botón de inicio de sesión con Google
            GoogleLoginButton(context = context, launcher = launcher)

            // Mostrar el diálogo de error si es necesario
            if (showErrorDialog.value) {
                ErrorDialog(
                    errorMessage = "Contraseña incorrecta",
                    onDismiss = { showErrorDialog.value = false }
                )
            }

        }
    }
}