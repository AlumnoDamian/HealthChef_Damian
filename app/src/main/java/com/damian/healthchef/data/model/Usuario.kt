package com.damian.healthchef.data.model

// Clase de modelo de datos que representa un usuario
data class Usuario(
    val id_usuario: String?, // Identificador único del usuario
    val nombre_usuario: String, // Nombre del usuario
    val correo_electronico: String, // Correo electrónico del usuario
    val contrasena: String // Contraseña del usuario
){
    // Método para convertir el objeto Usuario a un mapa mutable
    fun toMap(): MutableMap<String, Any>{
        return mutableMapOf(
            "nombre_usuario" to nombre_usuario, // Asigna el nombre de usuario al mapa
            "correo_electronico" to correo_electronico, // Asigna el correo electrónico al mapa
            "contrasena" to contrasena // Asigna la contraseña al mapa
        )
    }
}
