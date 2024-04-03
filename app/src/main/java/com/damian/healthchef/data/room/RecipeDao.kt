package com.damian.healthchef.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.damian.healthchef.data.model.Recipe
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao {

    /* RECIPES */
    @Query("SELECT * FROM recetas")
    fun getAllRecipes(): Flow<List<Recipe>>

    @Query("SELECT * FROM recetas WHERE id = :id")
    fun getRecipesById(id: Int): Flow<Recipe>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipe(recipe: Recipe)

    @Update
    suspend fun updateRecipe(recipe: Recipe)

    @Delete
    suspend fun deleteRecipe(recipe: Recipe)

    @Query("SELECT * FROM recetas WHERE nombre LIKE '%' || :query || '%' OR descripcion LIKE '%' || :query || '%'")
    fun searchRecipe(query: String): Flow<List<Recipe>>
    /* RECIPES */

}