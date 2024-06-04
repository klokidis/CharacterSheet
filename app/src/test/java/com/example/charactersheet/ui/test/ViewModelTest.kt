package com.example.charactersheet.ui.test

import com.example.charactersheet.R
import com.example.charactersheet.model.Artist
import com.example.charactersheet.ui.CharacterViewModel
import junit.framework.TestCase
import org.junit.Test

class ViewModelTest {

    private val viewModel = CharacterViewModel()

    @Test
    fun viewModel_CorrectAnswerGuessed_ScoreUpdated()  {
        viewModel.selectedArtist(Artist(R.string.name1, R.drawable.sage))
        val artist =  Artist(R.string.name1, R.drawable.sage)
        val currentUiState = viewModel.uiState.value
        TestCase.assertEquals(artist, currentUiState.artistChosen)
    }


}