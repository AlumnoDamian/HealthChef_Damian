package com.damian.healthchef.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.damian.healthchef.data.repository.RecipeRepository
import kotlinx.coroutines.launch

class RecipeViewModel(private val recipeRepository: RecipeRepository) : ViewModel() {

    val recipesLiveData = MutableLiveData<List<Recipe>>()
    val loadingLiveData = MutableLiveData<Boolean>()

    fun fetchRecipes(apiKey: String) {
        loadingLiveData.value = true
        viewModelScope.launch {
            try {
                val recipes = recipeRepository.fetchRecipes(apiKey)
                recipesLiveData.value = recipes
            } catch (e: Exception) {
                // Manejar errores aquí
                e.printStackTrace()
            } finally {
                loadingLiveData.value = false
            }
        }
    }
}