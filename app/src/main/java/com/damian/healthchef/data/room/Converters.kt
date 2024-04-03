package com.damian.healthchef.data.room

import androidx.room.TypeConverter

class Converters {
    @TypeConverter
    fun fromListToString(list: List<String>?): String? {
        return list?.joinToString(",")
    }

    @TypeConverter
    fun fromStringToList(value: String?): List<String>? {
        return value?.split(",")
    }
}