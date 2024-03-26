package com.damian.healthchef.data.usecase

/**
 * Clase que contiene instancias de casos de uso (Use Cases) relacionados con la API.
 * Esta clase actúa como un contenedor para los diferentes casos de uso que se utilizan en la aplicación.
 * @property getCategoriesUseCase Instancia del caso de uso para obtener categorías.
 * @property getMealsUseCase Instancia del caso de uso para obtener comidas por categoría.
 * @property getMealUseCase Instancia del caso de uso para obtener detalles de una comida por su ID.
 */
data class ApiUseCases(
    val getCategoriesUseCase: GetCategoriesUseCase,
    val getMealsUseCase: GetMealsUseCase,
    val getMealUseCase: GetMealUseCase
)