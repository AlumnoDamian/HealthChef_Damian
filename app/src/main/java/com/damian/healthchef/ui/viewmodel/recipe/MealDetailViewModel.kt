package com.damian.healthchef.ui.viewmodel.recipe

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.damian.healthchef.common.Constantes.PARAM_ID_MEAL
import com.damian.healthchef.common.Resource
import com.damian.healthchef.data.state.MealDetailState
import com.damian.healthchef.data.usecase.ApiUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealDetailViewModel @Inject constructor(
    private val apiUseCases: ApiUseCases,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _state = MutableStateFlow(MealDetailState())
    val state: StateFlow<MealDetailState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            savedStateHandle.get<String>(PARAM_ID_MEAL)?.let { idMeal ->
                getMeal(idMeal)
            }
        }
    }

    private suspend fun getMeal(idMeal: String) {
        apiUseCases.getMealUseCase(idMeal).onEach { result ->
            when(result) {
                is Resource.Success -> {
                    _state.value = MealDetailState(
                        meals = result.data?.meals ?: emptyList()
                    )
                }
                is Resource.Error -> {
                    _state.value = MealDetailState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _state.value = MealDetailState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}