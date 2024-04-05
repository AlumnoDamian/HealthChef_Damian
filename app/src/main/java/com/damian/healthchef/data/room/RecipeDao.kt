package com.damian.healthchef.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.damian.healthchef.data.model.Recipe
import kotlinx.coroutines.flow.Flow

// Interfaz que define métodos de acceso a la base de datos para la entidad Recipe
@Dao
interface RecipeDao {

    /* RECIPES */
    // Obtiene todas las recetas y las emite como un flujo de datos
    @Query("SELECT * FROM recetas")
    fun getAllRecipes(): Flow<List<Recipe>>

    // Obtiene una receta por su ID y la emite como un flujo de datos
    @Query("SELECT * FROM recetas WHERE id = :id")
    fun getRecipesById(id: Int): Flow<Recipe>

    // Inserta o reemplaza una receta en la base de datos
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipe(recipe: Recipe)

    // Actualiza una receta en la base de datos
    @Update
    suspend fun updateRecipe(recipe: Recipe)

    // Elimina una receta de la base de datos
    @Delete
    suspend fun deleteRecipe(recipe: Recipe)

    // Busca recetas que coincidan con el nombre o la descripción proporcionados y las emite como un flujo de datos
    @Query("SELECT * FROM recetas WHERE nombre LIKE '%' || :query || '%' OR descripcion LIKE '%' || :query || '%'")
    fun searchRecipe(query: String): Flow<List<Recipe>>
    /* RECIPES */

}