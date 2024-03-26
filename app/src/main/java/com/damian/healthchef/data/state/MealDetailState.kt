package com.damian.healthchef.data.state

import com.damian.healthchef.data.model.recipe.MealDetail

data class MealDetailState(
    val isLoading: Boolean = false,
    val meals: List<MealDetail> = emptyList(),
    val error: String = ""
)