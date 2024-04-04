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

    fun signInWithGoogleCredential(credential: AuthCredential, onLoginSucces: () -> Unit)
            = viewModelScope.launch {
        try {
            auth.signInWithCredential(credential)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d("HealthChef", "Logueado con Google exitoso!!")
                        onLoginSucces()
                        loadUserData()
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
                        loadUserData()
                        onLoginSucces()
                    } else {
                        Log.d("HealthChef", "Iniciar sesion: ${task.result.toString()} ")
                    }
                }
        } catch (ex: Exception) {
            Log.d("HealthChef", "Iniciar sesion: ${ex.message} ")
        }
    }

    fun signOut(onLogoutSuccess: () -> Unit) {
        auth.signOut()
        currentUser.value = null
        onLogoutSuccess()
    }
}