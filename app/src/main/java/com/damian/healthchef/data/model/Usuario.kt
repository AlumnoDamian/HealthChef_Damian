package com.damian.healthchef.data.model

data class Usuario(
    val id_usuario: String?,
    val nombre_usuario: String,
    val correo_electronico: String,
    val contrasena: String
){
    fun toMap(): MutableMap<String, Any>{
        return mutableMapOf(
            "nombre_usuario" to nombre_usuario,
            "correo_electronico" to correo_electronico,
            "contrasena" to contrasena
        )
    }
}