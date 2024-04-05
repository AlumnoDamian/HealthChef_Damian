package com.damian.healthchef.data.room

import androidx.room.TypeConverter

// Clase que proporciona métodos para convertir entre tipos de datos compatibles con Room
class Converters {
    // Convierte una lista de cadenas a una sola cadena separada por comas
    @TypeConverter
    fun fromListToString(list: List<String>?): String? {
        return list?.joinToString(",")
    }

    // Convierte una cadena separada por comas a una lista de cadenas
    @TypeConverter
    fun fromStringToList(value: String?): List<String>? {
        return value?.split(",")
    }
}