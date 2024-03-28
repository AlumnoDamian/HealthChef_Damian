package com.damian.healthchef.viewmodel.login



import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class RegisterViewModel : ViewModel() {
    private val auth: FirebaseAuth = Firebase.auth
    private val _loading = MutableLiveData(false)
    fun createUserWithEmailAndPassword(userName: String, email: String, password: String, onRegisterSucces: () -> Unit) {
        if (_loading.value == false){
            _loading.value = true
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        onRegisterSucces()
                    } else {
                        Log.d("HealthChef", "Crear usuario: ${task.result.toString()} ")
                    }
                }
            _loading.value = false
        }
    }
}