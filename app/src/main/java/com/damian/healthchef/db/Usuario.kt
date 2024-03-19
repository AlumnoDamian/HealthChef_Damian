package com.damian.healthchef.db

data class Usuario(
    val id_usuario: String?,
    val nombre_usuario: String,
    val correo_electronico: String,
    val cantidad_seguidores: Int,
    val cantidad_siguiendo: Int,
    val cantidad_post: Int,
){
    fun toMap(): MutableMap<String, Any>{
        return mutableMapOf(
            "nombre_usuario" to nombre_usuario,
            "correo_electronico" to correo_electronico,
            "cantidad_seguidores" to cantidad_seguidores,
            "cantidad_siguiendo" to cantidad_siguiendo,
            "cantidad_post" to cantidad_post
        )
    }
}
