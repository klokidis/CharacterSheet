package com.example.charactersheet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.charactersheet.model.Artist
import com.example.charactersheet.ui.theme.CharacterSheetTheme
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import com.example.charactersheet.data.Datasource

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CharacterSheetTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CharacterSheetApp()
                }
            }
        }
    }
}

@Composable
fun CharacterSheetApp() {
    ArtistList(Datasource().loadArtists())
}

@Composable
fun ArtistList(artistList: List<Artist>, modifier: Modifier = Modifier) {
    Column(Modifier
        .padding(top = 5.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = LocalContext.current.getString(R.string.Artists),
            style = MaterialTheme.typography.headlineMedium
        )
        LazyColumn(modifier = modifier) {
            items(artistList) { artist ->
                ArtistCard(
                    artist = artist,
                    modifier = Modifier
                        .padding(top = 10.dp, start = 10.dp, end = 10.dp)
                        .fillMaxWidth()
                )
            }
        }
    }
}

@Composable
fun ArtistCard(artist: Artist, modifier: Modifier = Modifier) {
    Card(modifier = modifier,
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )) {
        Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start) {
            Image(
                painter = painterResource(artist.imageResourceId),
                contentDescription = stringResource(artist.stringResourceId),
                modifier = Modifier
                    .size(width = 140.dp, height = 130.dp)
                    .padding(7.dp)
                    .clip(RoundedCornerShape(percent = 10)),
                contentScale = ContentScale.Crop
            )
            Text(
                text = LocalContext.current.getString(artist.stringResourceId),
                modifier = Modifier.padding(start = 10.dp, end = 16.dp),
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CharacterSheetTheme {
        ArtistCard(Artist(R.string.name1,R.drawable.artist1) )
    }
}