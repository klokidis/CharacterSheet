package com.example.charactersheet.ui

import androidx.lifecycle.ViewModel
import com.example.charactersheet.ui.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class CharacterViewModel : ViewModel(){
    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    init {

    }
}