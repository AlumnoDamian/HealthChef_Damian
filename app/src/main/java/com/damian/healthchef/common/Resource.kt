package com.damian.healthchef.common


/*
* Esta clase se utiliza para poder representar los diferentes
* estados de los datos que sean llamados de fuera de la app,
* como las Api de una web o imagenes, etc
*/

// Clase sellada que representa diferentes estados de un recurso
sealed class Resource<T>(
    val data: T? = null, // Datos del recurso (pueden ser nulos)
    val message: String? = null // Mensaje de error (puede ser nulo)
) {
    // Clase que representa un estado exitoso
    class Success<T>(data: T?) : Resource<T>(data)

    // Clase que representa un estado de error
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)

    // Clase que representa un estado de carga
    class Loading<T>(data: T? = null) : Resource<T>(data)
}