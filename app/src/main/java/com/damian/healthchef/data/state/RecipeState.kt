package com.damian.healthchef.data.state

import com.damian.healthchef.data.model.Recipe


data class RecipeState(
    val listRecipe: List<Recipe> = emptyList(),
)