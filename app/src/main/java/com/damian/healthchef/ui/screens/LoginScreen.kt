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
import com.damian.healthchef.ui.components.GoogleLoginButton
import com.damian.healthchef.ui.components.RegisterSection
import com.damian.healthchef.ui.components.SignInSection
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
    registerViewModel: RegisterViewModel = viewModel()
) {

    var showLoginForm by rememberSaveable { mutableStateOf(true) }

    val context = LocalContext.current

    val showErrorDialog = remember { mutableStateOf(false) }

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


    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = if (showLoginForm) "Iniciar Sesión" else "Registrarse",
                fontSize = 24.sp,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(bottom = 100.dp)
            )

            if (showLoginForm) {
                SignInSection(
                    viewModel = signInViewModel,
                    onLoginSuccess =  { email, password ->
                        signInViewModel.signInWithEmailAndPassword(email, password) {
                            navController.navigate(Screens.BottomBarScreens.Recipe.route)
                        }
                    },
                    onContinueRegister = { showLoginForm = false }
                )
            } else {
                RegisterSection(
                    viewModel = registerViewModel,
                    onRegisterSuccess = { username, email, password ->
                        registerViewModel.createUserWithEmailAndPassword(username, email, password) {
                            navController.navigate(Screens.BottomBarScreens.Login.route)
                        }
                    },
                    onContinueLogin = { showLoginForm = true },
                    onError = { showErrorDialog.value = true }
                )
            }

            Text(
                text = "----------------- o -----------------",
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(top = 24.dp, bottom = 24.dp)
            )

            GoogleLoginButton(context = context, launcher = launcher)

        }
    }
}