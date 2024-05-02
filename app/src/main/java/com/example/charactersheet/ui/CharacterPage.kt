package com.example.charactersheet.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.charactersheet.R
import com.example.charactersheet.model.Character
import com.example.charactersheet.ui.theme.CharacterSheetTheme

@Composable
fun CharacterPage(
    character : Character,
    navigateUp: () -> Unit
){
    val scrollState = rememberScrollState()
    val thisBackgroundColor =  MaterialTheme.colorScheme.background
    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(scrollState),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.8f),
            contentAlignment = Alignment.BottomStart
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.8f),
                contentAlignment = Alignment.TopStart
            ){
            Image(
                painter = painterResource(character.imageResourceId),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.8f),
                contentScale = ContentScale.Crop
            )
                if (scrollState.value == 0) { // Check if scroll position is at the top
                    IconButton(onClick = navigateUp) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.previous_button),
                            modifier = Modifier.size(36.dp)
                        )
                    }
                }
            }
            Text(
                text = LocalContext.current.getString(character.stringResourceId),
                style = MaterialTheme.typography.displayLarge.copy(
                    color = Color.White,
                    shadow = Shadow(
                        color = Color.Black,
                        offset = Offset(2f, 3f),
                        blurRadius = 6f
                    )
                ),
                color = Color.White,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                thisBackgroundColor.copy(alpha = 0.0f), // Transparent at the bottom
                                thisBackgroundColor.copy(alpha = 1.0f), // 50% opacity at the top
                            )
                        )
                    )
                    .padding(start = 30.dp, bottom = 10.dp, top = 10.dp)
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun CarcterPreview() {
    CharacterSheetTheme {
        CharacterPage(
            Character(R.string.neuvi,R.drawable.neuvi,R.string.neuvi),
            {}
        )
    }
}