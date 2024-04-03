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

class RecipeViewModel (private val dao: RecipeDao): ViewModel(){
    val listRecipe: Flow<List<Recipe>> = dao.getAllRecipes()

    var state by mutableStateOf(RecipeState())
        private set

    init {
        viewModelScope.launch {
            dao.getAllRecipes().collectLatest {
                state = state.copy(
                    listRecipe = it
                )
            }
        }
    }

    fun toggleFavorite(recipe: Recipe) = viewModelScope.launch {
        val currentRecipe = dao.getRecipesById(recipe.id).first()
        val updatedRecipe = currentRecipe.copy(
            isFavorite = !currentRecipe.isFavorite
        )
        dao.updateRecipe(updatedRecipe)
    }
    fun insertRecipe(recipe: Recipe) = viewModelScope.launch {
        dao.insertRecipe(recipe = recipe)
    }
    fun updateRecipe(recipe: Recipe) = viewModelScope.launch {
        dao.updateRecipe(recipe = recipe)
    }
    fun deleteRecipe(recipe: Recipe) = viewModelScope.launch {
        dao.deleteRecipe(recipe = recipe)
    }
    fun searchRecipe(query: String): Flow<List<Recipe>> {
        return dao.searchRecipe(query)
    }
}