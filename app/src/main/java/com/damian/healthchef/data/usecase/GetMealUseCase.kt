package com.damian.healthchef.data.usecase

import com.damian.healthchef.common.Resource
import com.damian.healthchef.data.model.response.MealDetailResponse
import com.damian.healthchef.data.repository.MealRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Caso de uso (Use Case) para obtener detalles de una comida por su ID.
 * Este caso de uso encapsula la lógica para obtener los detalles de una comida específica por su ID desde el repositorio.
 * @param repository El repositorio que proporciona los métodos para acceder a los datos.
 */
class GetMealUseCase @Inject constructor(
    private val repository: MealRepository
) {

    /**
     * Función de invocación que se puede utilizar para ejecutar el caso de uso.
     * En este caso, devuelve un flujo (Flow) que emite recursos (Resource) de tipo MealDetailResponse.
     * La función suspend se utiliza para realizar operaciones de manera asíncrona dentro del flujo.
     * @param idMeal El ID de la comida para la cual se desean obtener los detalles.
     * @return Un flujo (Flow) que emite recursos (Resource) de tipo MealDetailResponse.
     */
    suspend operator fun invoke(idMeal: String): Flow<Resource<MealDetailResponse>> {
        // Se llama al método getMealById() del repositorio para obtener los detalles de la comida por su ID.
        return repository.getMealById(idMeal)
    }

}