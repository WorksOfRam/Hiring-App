package com.srini.hiring.presentation.intent

sealed class HireIntent {
    data object LoadItems : HireIntent()
    data class ToggleExpand(val listId: Int) : HireIntent()
}