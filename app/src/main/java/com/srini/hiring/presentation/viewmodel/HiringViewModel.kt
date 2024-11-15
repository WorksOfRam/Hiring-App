package com.srini.hiring.presentation.viewmodel

import androidx.compose.runtime.mutableStateMapOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.srini.hiring.domain.usecase.HiringUseCase
import com.srini.hiring.presentation.intent.HireIntent
import com.srini.hiring.presentation.state.HireState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HiringViewModel @Inject constructor(
    private val useCase: HiringUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(HireState())
    val state: StateFlow<HireState> = _state

    fun handleIntent(intent: HireIntent) {
        when (intent) {
            is HireIntent.LoadItems -> fetchData()
            is HireIntent.ToggleExpand -> toggleExpanded(intent.listId)
        }
    }

    init {
        //fetchData()
    }

    // Track expanded state using a MutableStateMap
    private val _expandedState = mutableStateMapOf<Int, Boolean>()
    fun isExpanded(listId: Int) = _expandedState[listId] ?: false

    private fun toggleExpanded(listId: Int) {
        val currentState = _state.value
        _expandedState[listId] = !(_expandedState[listId] ?: false) // Toggle the expanded state
        _state.value = currentState.copy()
    }

    private fun fetchData() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            try {
                val data = useCase.getHiring()
                _state.value = _state.value.copy(
                    isLoading = false,
                    items = data,
                    error = null
                )
            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = e.message ?: "An error occurred"
                )
            }
        }
    }
}
