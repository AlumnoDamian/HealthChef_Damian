package com.damian.healthchef.viewmodel.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.damian.healthchef.data.model.Usuario
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore

class RegisterViewModel : ViewModel() {
    private val auth: FirebaseAuth = Firebase.auth
    private val _loading = MutableLiveData(false)
    fun createUserWithEmailAndPassword(
        email: String,
        password: String,
        onRegisterSucces: () -> Unit
    ) {
        if (_loading.value == false){
            _loading.value = true
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val displayName = task.result.user?.email?.split("@")?.get(0)
                        createUser(displayName, email, password)
                        onRegisterSucces()
                    } else {
                        Log.d("HealthChef", "Crear usuario: ${task.result.toString()} ")
                    }
                }
            _loading.value = false
        }
    }

    private fun createUser(displayName: String?, email: String, password: String) {
        val userId = auth.currentUser?.uid
        val user = Usuario(
            id_usuario = userId.toString(),
            nombre_usuario = displayName.toString(),
            correo_electronico = email,
            contrasena = password
        )

        FirebaseFirestore.getInstance().collection("usuarios")
            .add(user.toMap())
            .addOnSuccessListener {
                Log.d("HealthChef", "Creado ${it.id}")
            }.addOnFailureListener {
                Log.d("HealthChef", "Ocurrio error $it")
            }
    }
    private fun isEmailValid(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}