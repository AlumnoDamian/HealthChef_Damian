package com.damian.healthchef.data.usecase

import com.damian.healthchef.common.Resource
import com.damian.healthchef.data.model.response.MealsResponse
import com.damian.healthchef.data.repository.MealRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Caso de uso (Use Case) para obtener comidas por categoría.
 * Este caso de uso encapsula la lógica para obtener las comidas de una categoría específica desde el repositorio.
 * @param repository El repositorio que proporciona los métodos para acceder a los datos.
 */
class GetMealsUseCase @Inject constructor(
    private val repository: MealRepository
) {

    /**
     * Función de invocación que se puede utilizar para ejecutar el caso de uso.
     * En este caso, devuelve un flujo (Flow) que emite recursos (Resource) de tipo MealsResponse.
     * La función suspend se utiliza para realizar operaciones de manera asíncrona dentro del flujo.
     * @param strCategory El nombre de la categoría para la cual se desean obtener las comidas.
     * @return Un flujo (Flow) que emite recursos (Resource) de tipo MealsResponse.
     */
    suspend operator fun invoke(strCategory: String): Flow<Resource<MealsResponse>> {
        // Se llama al método getMealsByCategory() del repositorio para obtener las comidas por categoría.
        return repository.getMealsByCategory(strCategory)
    }
}