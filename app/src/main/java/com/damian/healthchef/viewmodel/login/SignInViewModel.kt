package com.damian.healthchef.viewmodel.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import kotlinx.coroutines.launch

class SignInViewModel: ViewModel() {
    private val auth: FirebaseAuth = Firebase.auth
    private val _loading = MutableLiveData(false)

    fun signInWithGoogleCredential(credential: AuthCredential, onLoginSucces: () -> Unit)
    = viewModelScope.launch {
        try {
            auth.signInWithCredential(credential)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d("HealthChef", "Logueado con Google exitoso!!")
                        onLoginSucces()
                    }
                }
                .addOnFailureListener {
                    Log.d("HealthChef", "Fallo al loguear con Google")
                }
        } catch (ex: Exception) {
            Log.d("HealthChef", "Excepcion al loguear con google: ${ex.localizedMessage} ")
        }

    }
    fun signInWithEmailAndPassword(email: String, password: String, onLoginSucces: () -> Unit)
    = viewModelScope.launch {
        try {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d("HealthChef", "Iniciar sesion: BIEN!")
                        onLoginSucces()
                    } else {
                        Log.d("HealthChef", "Iniciar sesion: ${task.result.toString()} ")
                    }
                }
        } catch (ex: Exception) {
            Log.d("HealthChef", "Iniciar sesion: ${ex.message} ")
        }
    }

}