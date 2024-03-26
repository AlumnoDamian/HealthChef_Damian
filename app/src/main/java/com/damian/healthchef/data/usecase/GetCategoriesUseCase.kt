package com.damian.healthchef.data.usecase

import com.damian.healthchef.common.Resource
import com.damian.healthchef.data.model.response.CategoryResponse
import com.damian.healthchef.data.repository.MealRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Caso de uso (Use Case) para obtener categorías.
 * Este caso de uso encapsula la lógica para obtener las categorías desde el repositorio.
 * @param repository El repositorio que proporciona los métodos para acceder a los datos.
 */
class GetCategoriesUseCase @Inject constructor(
    private val repository: MealRepository
) {

    /**
     * Función de invocación que se puede utilizar para ejecutar el caso de uso.
     * En este caso, devuelve un flujo (Flow) que emite recursos (Resource) de tipo CategoryResponse.
     * La función suspend se utiliza para realizar operaciones de manera asíncrona dentro del flujo.
     * @return Un flujo (Flow) que emite recursos (Resource) de tipo CategoryResponse.
     */
    suspend operator fun invoke(): Flow<Resource<CategoryResponse>> {
        // Se llama al método getCategories() del repositorio para obtener las categorías.
        return repository.getCategories()
    }

}