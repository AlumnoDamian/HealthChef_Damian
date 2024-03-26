package com.damian.healthchef.data.state

import com.damian.healthchef.data.model.recipe.Meals

data class MealsListState(
    val isLoading: Boolean = false,
    val meals: List<Meals> = emptyList(),
    val error: String = ""
)