package com.damian.healthchef.viewmodel.recipe

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.damian.healthchef.data.model.Recipe
import com.damian.healthchef.data.room.RecipeDao
import com.damian.healthchef.data.state.RecipeState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class RecipeViewModel(private val dao: RecipeDao) : ViewModel() {

    // Flujo de lista de recetas
    val listRecipe: Flow<List<Recipe>> = dao.getAllRecipes()

    // Estado mutable de la vista de recetas
    var state by mutableStateOf(RecipeState())
        private set

    init {
        // Inicializar el estado observando el flujo de recetas
        viewModelScope.launch {
            dao.getAllRecipes().collectLatest {
                state = state.copy(
                    listRecipe = it
                )
            }
        }
    }

    // Alternar el estado de favorito de una receta
    fun toggleFavorite(recipe: Recipe) = viewModelScope.launch {
        // Obtener la receta actual
        val currentRecipe = dao.getRecipesById(recipe.id).first()
        // Crear una copia de la receta con el estado de favorito invertido
        val updatedRecipe = currentRecipe.copy(
            isFavorite = !currentRecipe.isFavorite
        )
        // Actualizar la receta en la base de datos
        dao.updateRecipe(updatedRecipe)
    }

    // Insertar una nueva receta en la base de datos
    fun insertRecipe(recipe: Recipe) = viewModelScope.launch {
        dao.insertRecipe(recipe = recipe)
    }

    // Actualizar una receta existente en la base de datos
    fun updateRecipe(recipe: Recipe) = viewModelScope.launch {
        dao.updateRecipe(recipe = recipe)
    }

    // Eliminar una receta de la base de datos
    fun deleteRecipe(recipe: Recipe) = viewModelScope.launch {
        dao.deleteRecipe(recipe = recipe)
    }

    // Buscar recetas que coincidan con el término de búsqueda
    fun searchRecipe(query: String): Flow<List<Recipe>> {
        return dao.searchRecipe(query)
    }
}