package com.example.charactersheet.ui

import androidx.annotation.StringRes
import com.example.charactersheet.data.Datasource
import com.example.charactersheet.model.Artist
import com.example.charactersheet.model.Character

data class UiState(
    @StringRes
    val titleBar: Int = 0,

    val artistChosen : Artist = Artist(0,0),
    val characterChosen : Character = Character(0,0,0),

    val listOfCharacters: List<Character> = Datasource().loadCharacters(),
    val listOfArtists: List<Artist> = Datasource().loadArtists(),
)