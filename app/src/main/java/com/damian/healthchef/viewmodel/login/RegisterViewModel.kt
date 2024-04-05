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

    // Método para crear un usuario con correo electrónico y contraseña
    fun createUserWithEmailAndPassword(
        email: String,
        password: String,
        onRegisterSucces: () -> Unit
    ) {
        // Verificar que el proceso de carga no esté activo
        if (_loading.value == false) {
            _loading.value = true // Establecer el estado de carga en verdadero
            // Crear usuario con correo electrónico y contraseña en Firebase Authentication
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) { // Verificar si la creación del usuario fue exitosa
                        // Obtener el nombre de usuario a partir del correo electrónico
                        val displayName = task.result.user?.email?.split("@")?.get(0)
                        // Crear el usuario en la base de datos Firestore
                        createUser(displayName, email, password)
                        onRegisterSucces() // Llamar a la función de registro exitoso
                    } else {
                        // Mostrar un mensaje de registro fallido en el registro
                        Log.d("HealthChef", "Crear usuario: ${task.result.toString()} ")
                    }
                }
            _loading.value = false // Establecer el estado de carga en falso
        }
    }

    // Método privado para crear un usuario en Firestore
    private fun createUser(displayName: String?, email: String, password: String) {
        val userId = auth.currentUser?.uid // Obtener el ID del usuario actualmente autenticado
        val user = Usuario(
            id_usuario = userId.toString(),
            nombre_usuario = displayName.toString(),
            correo_electronico = email,
            contrasena = password
        )
        // Agregar el usuario a la colección de usuarios en Firestore
        FirebaseFirestore.getInstance().collection("usuarios")
            .add(user.toMap())
            .addOnSuccessListener {
                Log.d("HealthChef", "Creado ${it.id}") // Lograr el éxito en la creación del usuario
            }.addOnFailureListener {
                Log.d("HealthChef", "Ocurrio error $it") // Lograr el error en la creación del usuario
            }
    }
}