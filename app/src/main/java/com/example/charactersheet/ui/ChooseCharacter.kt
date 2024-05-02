package com.example.charactersheet.ui


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.charactersheet.R
import com.example.charactersheet.model.Character
import com.example.charactersheet.ui.theme.CharacterSheetTheme

@Composable
fun ChooseCharacter(
    onButtonCard: (Character) -> Unit,
    characterList: List<Character>,
    modifier: Modifier = Modifier
){
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier
            .padding(start = 10.dp, end = 10.dp)
            .fillMaxSize()
        ) {
        items(characterList) { thisCharacter ->
            CharacterCard(
                character = thisCharacter,
                {onButtonCard(thisCharacter)},
                modifier = modifier
                    .padding(
                        top = dimensionResource(R.dimen.padding_medium),
                        start = dimensionResource(R.dimen.padding_medium),
                        end = dimensionResource(R.dimen.padding_medium),
                    ),
            )
        }
    }
}

@Composable
fun CharacterCard(character: Character,onButtonCard: () -> Unit, modifier: Modifier) {
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
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = LocalContext.current.getString(character.stringResourceId),
                style = MaterialTheme.typography.titleMedium,
            )
            Spacer(modifier = Modifier.height(6.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    CharacterSheetTheme {
        ChooseCharacter(
            {Character(0,0,0) } ,
            listOf<Character>(
                Character(R.string.name1, R.drawable.artist1,R.string.name1),
                Character(R.string.name1, R.drawable.artist1,R.string.name1),
                Character(R.string.name1, R.drawable.artist1,R.string.name1),
                Character(R.string.name1, R.drawable.artist1,R.string.name1),
                Character(R.string.name1, R.drawable.artist1,R.string.name1),
                Character(R.string.name1, R.drawable.artist1,R.string.name1),
                Character(R.string.name1, R.drawable.artist1,R.string.name1),
                Character(R.string.name1, R.drawable.artist1,R.string.name1),
            ))
    }
}
