package com.example.charactersheet.ui


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.charactersheet.R
import com.example.charactersheet.model.Character
import com.example.charactersheet.ui.theme.CharacterSheetTheme

@Composable
fun ChooseCharacter(
    onButtonCard: (Character) -> Unit,
    characterList: List<Character>,
) {
    val scrollState = rememberScrollState()
  //  val thisBackgroundColor = MaterialTheme.colorScheme.background

    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = characterList[0].imageResourceId),
            contentDescription = null,
            modifier = Modifier
                .weight(1f),
            contentScale = ContentScale.Crop
        )
        LazyRow(
            modifier = Modifier.fillMaxSize()
        ) {
            items(characterList) { character ->
                CharCard(character,onButtonCard)
            }
        }
        Spacer(modifier = Modifier.padding(2.dp))
    }
}

@Composable
fun CharCard(character: Character, onButtonCard: (Character) -> Unit) {
    Card(
        shape = RoundedCornerShape(0.dp),
        modifier = Modifier
            .clickable {
            }
            .padding(end = 3.dp, top = 3.dp, start = 3.dp)
            .wrapContentSize(),
        onClick = {onButtonCard(character)}
    ) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color.Black.copy(alpha = 0.3f), // Transparent at the bottom
                        Color.Black.copy(alpha = 0.9f), // 50% opacity at the top
                    )
                )
            ),
        contentAlignment = Alignment.BottomCenter
    ) {
        Image(
            painter = painterResource(
                id = character.imageResourceId
            ),
            contentDescription = null,
            modifier = Modifier
                .width(130.dp)
                .height(180.dp)
                .border(
                    BorderStroke(2.dp, MaterialTheme.colorScheme.onSurface)
                ),
            contentScale = ContentScale.Crop
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(id = character.stringResourceId),
            style = MaterialTheme.typography.headlineLarge.copy(
                color = Color.White,
                shadow = Shadow(
                    color = Color.Black,
                    offset = Offset(2f, 3f),
                    blurRadius = 6f
                )
            ),
            color = Color.White,
            textAlign = TextAlign.Center,
        )
    }

    }
}

//    LazyVerticalGrid(
//        columns = GridCells.Fixed(2),
//        verticalArrangement = Arrangement.spacedBy(10.dp),
//        modifier = Modifier
//            .padding(start = 10.dp, end = 10.dp)
//            .fillMaxSize()
//        ) {
//        items(characterList) { thisCharacter ->
//            CharacterCard(
//                character = thisCharacter,
//                {onButtonCard(thisCharacter)},
//                modifier = modifier
//                    .padding(
//                        top = dimensionResource(R.dimen.padding_medium),
//                        start = dimensionResource(R.dimen.padding_medium),
//                        end = dimensionResource(R.dimen.padding_medium),
//                    ),
//            )
//        }
//    }

@Composable
fun CharacterCard(character: Character, onButtonCard: () -> Unit, modifier: Modifier) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.inverseOnSurface,
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = dimensionResource(R.dimen.elevation_image),
        ),
        modifier = modifier
            .clip(MaterialTheme.shapes.medium)
            .wrapContentSize()
            .padding(bottom = 5.dp)
            .shadow(elevation = 2.dp, shape = RoundedCornerShape(16.dp)),

        onClick = onButtonCard,
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(character.imageResourceId),
                contentDescription = null, //no need
                modifier = Modifier
                    .size(width = 140.dp, height = 165.dp),
                contentScale = ContentScale.Crop
            )
            HorizontalDivider(
                modifier = Modifier
                    .size(width = 140.dp, height = 1.dp)  //fill the max height
                    .width(1.dp),
                color = Color.DarkGray
            )
            Spacer(modifier = Modifier.height(3.dp))
            Text(
                text = LocalContext.current.getString(character.stringResourceId),
                style = MaterialTheme.typography.titleMedium,
            )
            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    CharacterSheetTheme {
        ChooseCharacter(
            { Character(0, 0, 0) },
            listOf(
                Character(R.string.neuvi, R.drawable.neuvi, R.string.neuvi),
                Character(R.string.name1, R.drawable.artist1, R.string.name1),
                Character(R.string.name1, R.drawable.artist1, R.string.name1),
                Character(R.string.name1, R.drawable.artist1, R.string.name1),
                Character(R.string.name1, R.drawable.artist1, R.string.name1),
                Character(R.string.name1, R.drawable.artist1, R.string.name1),
                Character(R.string.name1, R.drawable.artist1, R.string.name1),
                Character(R.string.name1, R.drawable.artist1, R.string.name1),
            )
        )
    }
}
