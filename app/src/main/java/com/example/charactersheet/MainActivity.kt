package com.example.charactersheet

import android.os.Bundle
import android.util.Log
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
import com.example.charactersheet.model.Artist
import com.example.charactersheet.ui.theme.CharacterSheetTheme
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.dimensionResource
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.draw.clip
import com.example.charactersheet.data.Datasource

private const val TAG = "MainActivity"
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
    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart Called")
    }
    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume Called")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart Called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause Called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop Called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy Called")
    }
}

@Composable
fun CharacterSheetApp() {
    ArtistList(Datasource().loadArtists())
}

@Composable
fun ArtistList(artistList: List<Artist>, modifier: Modifier = Modifier) {
    Scaffold(
        topBar ={
            TopAppBar()
        }
    ) { it ->
        LazyColumn(contentPadding = it)  {
            items(artistList) { artist ->
                ArtistCard(
                    artist = artist,
                    modifier = modifier
                        .padding(
                            top = dimensionResource(R.dimen.padding_medium),
                            start = dimensionResource(R.dimen.padding_medium),
                            end = dimensionResource(R.dimen.padding_medium)
                        )
                )
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = LocalContext.current.getString(R.string.Artists),
                style = MaterialTheme.typography.displayLarge
            )
        },
        modifier = modifier
    )
}

@Composable
fun ArtistCard(artist: Artist, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.medium)
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
                style = MaterialTheme.typography.titleLarge,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CharacterSheetTheme {
        ArtistList(Datasource().loadArtists())
    }
}