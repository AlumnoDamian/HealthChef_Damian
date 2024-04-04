package com.damian.healthchef.data.model

import android.net.Uri
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recetas")
data class Recipe(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo("Nombre")
    val nombre: String,
    @ColumnInfo("Descripcion")
    val descripcion: String,
    @ColumnInfo(name = "Ingredientes")
    val ingredientes: String,
    @ColumnInfo(name = "Instrucciones")
    val instrucciones: String,
    @ColumnInfo(name = "Tiempo_de_preparacion")
    val tiempoDePreparacion: String,
    @ColumnInfo(name = "Calorias")
    val calorias: String,
    @ColumnInfo(name = "Grasas")
    val grasas: String,
    @ColumnInfo(name = "Proteinas")
    val proteinas: String,
    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
)