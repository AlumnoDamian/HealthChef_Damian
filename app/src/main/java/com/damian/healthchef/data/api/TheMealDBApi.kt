package com.damian.healthchef.data.api

import com.damian.healthchef.data.model.response.CategoryResponse
import com.damian.healthchef.data.model.response.MealDetailResponse
import com.damian.healthchef.data.model.response.MealsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface TheMealDBApi {
    @GET("categories.php")
    suspend fun getCategories(): CategoryResponse

    @GET("filter.php")
    suspend fun getMealsByCategory(@Query("c") strCategory: String): MealsResponse

    @GET("lookup.php")
    suspend fun getMealById(@Query("i") idMeal: String): MealDetailResponse
}