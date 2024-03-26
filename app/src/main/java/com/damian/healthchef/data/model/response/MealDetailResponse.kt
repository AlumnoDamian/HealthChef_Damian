package com.damian.healthchef.data.model.response

import com.damian.healthchef.data.model.recipe.MealDetail

data class MealDetailResponse(
    val meals: List<MealDetail>
)