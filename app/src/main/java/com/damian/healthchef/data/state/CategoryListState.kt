package com.damian.healthchef.data.state

import com.damian.healthchef.data.model.recipe.Category

data class CategoryListState(
    val isLoading: Boolean = false,
    val categories: List<Category> = emptyList(),
    val error: String = ""
)