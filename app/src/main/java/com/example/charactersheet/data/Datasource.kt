package com.example.charactersheet.data

import com.example.charactersheet.R
import com.example.charactersheet.model.Artist
import com.example.charactersheet.model.Character
class Datasource() {
    fun loadArtists(): List<Artist> {
        return listOf(
            Artist(R.string.name1, R.drawable.sage),
            Artist(R.string.name1, R.drawable.sage),
            Artist(R.string.name1, R.drawable.sage),
            Artist(R.string.name1, R.drawable.sage),
        )
    }
    fun loadCharacters(): List<Character> {
        return listOf(
            Character(R.string.name1, R.drawable.sage,R.string.name1),
            Character(R.string.name1, R.drawable.sage,R.string.name1),
            Character(R.string.name1, R.drawable.sage,R.string.name1),
            Character(R.string.name1, R.drawable.sage,R.string.name1),
            Character(R.string.name1, R.drawable.sage,R.string.name1),
            )
    }
}