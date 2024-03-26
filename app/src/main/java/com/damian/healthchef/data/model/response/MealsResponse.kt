package com.damian.healthchef.data.model.response

import com.damian.healthchef.data.model.recipe.Meals

data class MealsResponse(
    val meals: List<Meals>
)