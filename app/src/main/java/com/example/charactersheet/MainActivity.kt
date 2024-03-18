package com.example.charactersheet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.charactersheet.model.Artist
import com.example.charactersheet.ui.theme.CharacterSheetTheme
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import com.example.charactersheet.data.Datasource

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CharacterSheetTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize().statusBarsPadding(),
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
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = LocalContext.current.getString(R.string.Artists),
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold)
        )
        LazyColumn{
            items(artistList) { artist ->
                ArtistCard(
                    artist = artist,
                    modifier = modifier
                        .padding(top = dimensionResource(R.dimen.padding_medium), start = dimensionResource(R.dimen.padding_medium), end = dimensionResource(R.dimen.padding_medium))
                )
            }
        }
    }
}

@Composable
fun ArtistCard(artist: Artist, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(percent = 10))
            .clickable {
        },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = dimensionResource(R.dimen.elevation_image),
        )) {
        Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start) {
            Box {
                Image(
                    painter = painterResource(artist.imageResourceId),
                    contentDescription = null, //no need
                    modifier = Modifier
                        .size(width = dimensionResource(R.dimen.image_width), height = dimensionResource(R.dimen.image_height))
                        .padding(end = dimensionResource(R.dimen.padding_small))
                        .aspectRatio(1f),
                    contentScale = ContentScale.Crop
                )
            }
            Text(
                text = LocalContext.current.getString(artist.stringResourceId),
                modifier = Modifier.padding(start = dimensionResource(R.dimen.padding_medium)),
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.SemiBold),
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