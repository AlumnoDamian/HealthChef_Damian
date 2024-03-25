package com.damian.healthchef.data.repository

import com.damian.healthchef.common.Resource
import com.damian.healthchef.data.model.recipe.CategoryResponse
import com.damian.healthchef.data.model.recipe.MealDetailResponse
import com.damian.healthchef.data.model.recipe.MealsResponse
import kotlinx.coroutines.flow.Flow

interface MealRepository {

    suspend fun getCategories(): Flow<Resource<CategoryResponse>>

    suspend fun getMealsByCategory(strCategory: String): Flow<Resource<MealsResponse>>

    suspend fun getMealById(idMeal: String): Flow<Resource<MealDetailResponse>>

}