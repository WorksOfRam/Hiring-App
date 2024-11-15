package com.srini.hiring.presentation.state

import com.srini.hiring.data.model.HireModel

data class HireState(
    val isLoading: Boolean = true,
    val items: Map<Int, List<HireModel>> = emptyMap(),
    val error: String? = null
)