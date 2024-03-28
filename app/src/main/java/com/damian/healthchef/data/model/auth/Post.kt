package com.damian.healthchef.data.model.auth

data class Post(
    val id: String,
    val contenido: String,
    val fechaPublicacion: Long // Puedes usar un timestamp para la fecha de publicación
)
