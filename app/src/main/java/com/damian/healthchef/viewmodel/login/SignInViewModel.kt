package com.damian.healthchef.viewmodel.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.damian.healthchef.data.model.Usuario
import com.google.firebase.Firebase
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class SignInViewModel: ViewModel() {

    private val auth: FirebaseAuth = Firebase.auth
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    val currentUser = MutableLiveData<String?>()

    // Cargar los datos del usuario actual
    fun loadUserData() {
        val userId = auth.currentUser?.uid
        if (userId != null) {
            viewModelScope.launch {
                val userRef = firestore.collection("usuarios").document(userId)
                val userSnapshot = userRef.get().await()

                if (userSnapshot.exists()) {
                    val user = userSnapshot.toObject<Usuario>()
                    currentUser.value = user?.nombre_usuario
                }
            }
        }
    }

    // Iniciar sesión con credenciales de Google
    fun signInWithGoogleCredential(credential: AuthCredential, onLoginSucces: () -> Unit)
            = viewModelScope.launch {
        try {
            auth.signInWithCredential(credential)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d("HealthChef", "Logueado con Google exitoso!!")
                        onLoginSucces()
                        loadUserData() // Cargar datos del usuario después del inicio de sesión exitoso
                    }
                }
                .addOnFailureListener {
                    Log.d("HealthChef", "Fallo al loguear con Google")
                }
        } catch (ex: Exception) {
            Log.d("HealthChef", "Excepcion al loguear con google: ${ex.localizedMessage} ")
        }
    }

    // Iniciar sesión con correo electrónico y contraseña
    fun signInWithEmailAndPassword(email: String, password: String, onLoginSuccess: () -> Unit,  onError: (String) -> Unit)
            = viewModelScope.launch {
        try {
            if (!isEmailValid(email)) {
                onError("El formato del correo electrónico es inválido.")
                return@launch
            }
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d("HealthChef", "Iniciar sesion: BIEN!")
                        if (task.result?.user?.email == email && task.result?.user?.uid != null) {
                            loadUserData() // Cargar datos del usuario después del inicio de sesión exitoso
                            onLoginSuccess()
                        } else {
                            Log.d("HealthChef", "Iniciar sesión: Contraseña incorrecta")
                            onError("Valores incorrectos o inexistentes")
                        }
                    } else {
                        Log.d("HealthChef", "Iniciar sesion: ${task.result.toString()} ")
                    }
                }
        } catch (ex: Exception) {
            Log.d("HealthChef", "Iniciar sesion: ${ex.message} ")
        }
    }

    // Verificar si el formato del correo electrónico es válido
    fun isEmailValid(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    // Cerrar sesión
    fun signOut(onLogoutSuccess: () -> Unit) {
        auth.signOut() // Cerrar sesión en Firebase Authentication
        currentUser.value = null // Restablecer el usuario actual
        onLogoutSuccess() // Llamar a la función de cierre de sesión exitoso
    }
}