package com.damian.healthchef.data.repository

import com.damian.healthchef.common.Resource
import com.damian.healthchef.data.api.TheMealDBApi
import com.damian.healthchef.data.model.response.CategoryResponse
import com.damian.healthchef.data.model.response.MealDetailResponse
import com.damian.healthchef.data.model.response.MealsResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

// Implementación del repositorio de comidas
class MealRepositoryImpl @Inject constructor(
    private val api: TheMealDBApi // Inyección de dependencias de la interfaz de la API
) : MealRepository {

    // Función para obtener las categorías de recetas
    override suspend fun getCategories(): Flow<Resource<CategoryResponse>> = flow {
        try {
            emit(Resource.Loading()) // Emite un estado de carga antes de realizar la solicitud
            val categories = api.getCategories() // Realiza la solicitud a la API para obtener las categorías
            emit(Resource.Success(categories)) // Emite un estado de éxito con las categorías obtenidas
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred")) // Maneja errores HTTP
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection")) // Maneja errores de red
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred")) // Maneja errores inesperados
        }
    }

    // Función para obtener comidas filtradas por categoría
    override suspend fun getMealsByCategory(strCategory: String): Flow<Resource<MealsResponse>> = flow {
        try {
            emit(Resource.Loading()) // Emite un estado de carga antes de realizar la solicitud
            val meals = api.getMealsByCategory(strCategory) // Realiza la solicitud a la API para obtener las comidas
            emit(Resource.Success(meals)) // Emite un estado de éxito con las comidas obtenidas
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred")) // Maneja errores HTTP
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection")) // Maneja errores de red
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred")) // Maneja errores inesperados
        }
    }

    // Función para obtener detalles de una comida por su ID
    override suspend fun getMealById(idMeal: String): Flow<Resource<MealDetailResponse>> = flow {
        try {
            emit(Resource.Loading()) // Emite un estado de carga antes de realizar la solicitud
            val meals = api.getMealById(idMeal) // Realiza la solicitud a la API para obtener los detalles de la comida
            emit(Resource.Success(meals)) // Emite un estado de éxito con los detalles de la comida obtenidos
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred")) // Maneja errores HTTP
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection")) // Maneja errores de red
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred")) // Maneja errores inesperados
        }
    }
}