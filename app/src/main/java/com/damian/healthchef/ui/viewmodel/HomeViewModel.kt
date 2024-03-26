
package com.damian.healthchef.ui.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.damian.healthchef.data.state.BlogState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class BlogViewModel : ViewModel() {

    private val _state = mutableStateOf(BlogState())
    val state: State<BlogState> = _state

    fun loadingRefresh() {
        _state.value = _state.value.copy(isLoading = true)

        viewModelScope.launch {
            delay(2500)
            _state.value = _state.value.copy(isLoading = false)
        }
    }

}