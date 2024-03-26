package com.damian.healthchef.data.repository

import com.damian.healthchef.common.Resource
import com.damian.healthchef.data.model.response.CategoryResponse
import com.damian.healthchef.data.model.response.MealDetailResponse
import com.damian.healthchef.data.model.response.MealsResponse
import kotlinx.coroutines.flow.Flow

// Interfaz que define métodos para obtener datos relacionados con recetas de comidas
interface MealRepository {

    // Función para obtener las categorías de recetas
    suspend fun getCategories(): Flow<Resource<CategoryResponse>>

    // Función para obtener comidas filtradas por categoría
    suspend fun getMealsByCategory(strCategory: String): Flow<Resource<MealsResponse>>

    // Función para obtener detalles de una comida por su ID
    suspend fun getMealById(idMeal: String): Flow<Resource<MealDetailResponse>>

}