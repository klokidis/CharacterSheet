package com.example.charactersheet.ui


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.charactersheet.R
import com.example.charactersheet.model.Character
import com.example.charactersheet.ui.theme.CharacterSheetTheme


@Composable
fun ChooseCharacter(
    onButtonCard: (Character) -> Unit,
    characterList: List<Character>,
) {
    val thisBackgroundColor = MaterialTheme.colorScheme.background
    val textColor: Color
    val blurColor: Color

    if(thisBackgroundColor == Color(0xFFFDFCFF)){ // if is in light mode
        textColor = Color.Black
        blurColor = Color.White
    }else{
        textColor = Color.White
        blurColor = Color.Black
    }
    LazyVerticalGrid(
        columns = GridCells.Fixed(2), //Cards per row
        modifier = Modifier
            .padding(start = 10.dp, end = 10.dp, top = 10.dp)
            .fillMaxSize()
    ) {
        items(characterList) { thisCharacter ->
            Box(
                modifier = Modifier
                    .height(dimensionResource(id = R.dimen.character_box))
                    .fillMaxWidth()
                    .padding(bottom = 20.dp, top = 20.dp)
            ) {
                CharacterCard(
                    { onButtonCard(thisCharacter) },
                    modifier = Modifier
                        .padding(
                            top = 30.dp,
                            start = dimensionResource(R.dimen.padding_medium),
                            end = dimensionResource(R.dimen.padding_medium),
                        ),
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    contentAlignment = Alignment.BottomEnd
                ) {
                    Image(
                        painter = painterResource(
                            id = thisCharacter.imageResourceId
                        ),
                        contentDescription =
                        null,
                        modifier = Modifier
                            .fillMaxHeight()
                            .padding(bottom = 10.dp, top = 10.dp, end = 5.dp),
                        contentScale = ContentScale.Fit
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(bottom = 20.dp, start = 20.dp, end = 20.dp),
                        contentAlignment = Alignment.BottomStart
                    ) {
                        Text(
                            text = stringResource(thisCharacter.stringResourceId),
                            style = MaterialTheme.typography.titleLarge.copy(
                                fontSize = 30.sp,
                                color = textColor,
                                shadow = Shadow(
                                    color = blurColor,
                                    offset = Offset(1f, 1f),
                                    blurRadius = 1f
                                )
                            )
                        )
                    }

                }
            }
        }
    }
}

@Composable
fun CharacterCard(onButtonCard: () -> Unit, modifier: Modifier) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.inverseOnSurface,
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = dimensionResource(R.dimen.elevation_image),
        ),
        modifier = modifier
            .clip(MaterialTheme.shapes.medium)
            .height(dimensionResource(id = R.dimen.character_card))
            .fillMaxWidth()
            .shadow(elevation = 2.dp, shape = RoundedCornerShape(16.dp)),

        onClick = onButtonCard,
    ) {
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    CharacterSheetTheme {
        ChooseCharacter(
            { Character(0, 0, 0) },
            listOf(
                Character(R.string.app_name, R.drawable.sage, R.string.neuvi),
                Character(R.string.neuvi, R.drawable.sage, R.string.name1),
                Character(R.string.neuvi, R.drawable.sage, R.string.name1),
                Character(R.string.neuvi, R.drawable.sage, R.string.name1),
                Character(R.string.neuvi, R.drawable.sage, R.string.name1),
                Character(R.string.neuvi, R.drawable.sage, R.string.name1),
                Character(R.string.neuvi, R.drawable.sage, R.string.name1),
            )
        )
    }
}
