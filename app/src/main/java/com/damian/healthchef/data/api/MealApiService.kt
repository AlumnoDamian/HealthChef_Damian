package com.damian.healthchef.data.api

import com.damian.healthchef.data.model.recipe.CategoryResponse
import com.damian.healthchef.data.model.recipe.MealDetailResponse
import com.damian.healthchef.data.model.recipe.MealsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MealApiService {
    @GET("categories.php")
    suspend fun getCategories(): CategoryResponse

    @GET("filter.php")
    suspend fun getMealsByCategory(@Query("c") strCategory: String): MealsResponse

    @GET("lookup.php")
    suspend fun getMealById(@Query("i") idMeal: String): MealDetailResponse
}