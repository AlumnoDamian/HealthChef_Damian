package com.damian.healthchef.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.damian.healthchef.data.model.Recipe

// Anotación para declarar la base de datos
@Database(
    entities = [Recipe::class], // Entidades incluidas en la base de datos
    version = 1, // Versión de la base de datos
    exportSchema = false // No exportar el esquema de la base de datos
)
// Se especifica la clase de los convertidores de tipo para la base de datos
@TypeConverters(Converters::class)
// Clase abstracta que extiende RoomDatabase
abstract class RecipeDatabase : RoomDatabase() {

    // Método abstracto para obtener el DAO de recetas
    abstract fun recipeDao(): RecipeDao

    // Patrón Singleton para obtener una instancia de la base de datos
    companion object {
        @Volatile
        private var INSTANCE: RecipeDatabase? = null

        // Método para obtener una instancia de la base de datos
        fun getInstance(context: Context): RecipeDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RecipeDatabase::class.java,
                    "recetas_database" // Nombre de la base de datos
                )
                    .fallbackToDestructiveMigration() // Permite la migración destructiva
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}