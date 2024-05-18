package com.example.charactersheet.ui

import androidx.lifecycle.ViewModel
import com.example.charactersheet.R
import com.example.charactersheet.data.Datasource
import com.example.charactersheet.model.Artist
import com.example.charactersheet.model.Character
import com.example.charactersheet.model.TabItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CharacterViewModel : ViewModel(){
    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    init {
        resetArtistChosen()
    }

    fun selectedArtist(artist: Artist){
        _uiState.update { currentState ->
            currentState.copy(
                artistChosen = artist,
                listOfCharacters = _uiState.value.listOfCharacters.filter { it.artist == artist.stringResourceId }
            )
        }
    }
    fun selectedCharacter(character: Character){
        _uiState.update { currentState ->
            currentState.copy(
                characterChosen = character
            )
        }
    }
    fun resetArtistChosen(){
        _uiState.update { currentState ->
            currentState.copy(
                listOfCharacters = Datasource().loadCharacters()
            )
        }
    }

    val tabItems = listOf(
        TabItem(
            title = R.string.profile_tab
        ),
        TabItem(
            title = R.string.story_tab
        ),
        TabItem(
            title = R.string.drawings
        ),
    )
}