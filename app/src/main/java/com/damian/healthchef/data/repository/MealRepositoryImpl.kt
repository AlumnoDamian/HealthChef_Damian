package com.damian.healthchef.data.repository

import com.damian.healthchef.common.Resource
import com.damian.healthchef.data.api.MealApiService
import com.damian.healthchef.data.model.recipe.CategoryResponse
import com.damian.healthchef.data.model.recipe.MealDetailResponse
import com.damian.healthchef.data.model.recipe.MealsResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class MealRepositoryImpl @Inject constructor(
    private val api: MealApiService
) : MealRepository {

    override suspend fun getCategories(): Flow<Resource<CategoryResponse>> = flow {
        try {
            emit(Resource.Loading())
            val categories = api.getCategories()
            emit(Resource.Success(categories))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection"))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        }
    }

    override suspend fun getMealsByCategory(strCategory: String): Flow<Resource<MealsResponse>> = flow {
        try {
            emit(Resource.Loading())
            val meals = api.getMealsByCategory(strCategory)
            emit(Resource.Success(meals))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection"))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        }
    }

    override suspend fun getMealById(idMeal: String): Flow<Resource<MealDetailResponse>> = flow {
        try {
            emit(Resource.Loading())
            val meals = api.getMealById(idMeal)
            emit(Resource.Success(meals))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection"))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        }
    }

}