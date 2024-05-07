package com.example.charactersheet.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
fun CharacterPage(
    character: Character,
    navigateUp: () -> Unit
) {
    val scrollState = rememberScrollState()
    val thisBackgroundColor = MaterialTheme.colorScheme.background

    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(scrollState),
    ) {
        Box( // text on top of image to bottom start
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(1f),
            contentAlignment = Alignment.BottomStart
        ) {
            Box( // arrow on top of image to top start
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(1f),
                contentAlignment = Alignment.TopStart
            ) {
                Image(
                    painter = painterResource(character.imageResourceId),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(1f),
                    contentScale = ContentScale.Crop
                )
                if (scrollState.value == 0) { // Check if scroll position is at the top
                    IconButton(onClick = navigateUp) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.previous_button),
                            modifier = Modifier.size(dimensionResource(id = R.dimen.icon_character))
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
                        offset = Offset(3f, 3f),
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
        TabRowBar(character)
    }
}

@Composable
fun Profile(character: Character) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier
                .wrapContentSize()
                .fillMaxWidth()
                .padding(15.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(15.dp)
            ) {
                Text(
                    text = stringResource(
                        R.string.name,
                        " " + stringResource(character.stringResourceId)
                    )
                )
                Spacer(modifier = Modifier.padding(4.dp))
                Text(
                    text = stringResource(
                        R.string.age,
                        " " + stringResource(character.stringResourceId)
                    )
                )
                Spacer(modifier = Modifier.padding(4.dp))
                Text(
                    text = stringResource(
                        R.string.sex,
                        " " + stringResource(character.stringResourceId)
                    )
                )
                Spacer(modifier = Modifier.padding(4.dp))
                Text(
                    text = stringResource(
                        R.string.birth_date,
                        " " + stringResource(character.stringResourceId)
                    )
                )
            }

        }
    }
}

//@Composable
//fun profileCard(){
//
//}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TabRowBar(character: Character) {

    var selectedTabIndex by rememberSaveable {
        mutableIntStateOf(0)
    }

    val pagerState = rememberPagerState {
        CharacterViewModel().tabItems.size
    }

    LaunchedEffect(selectedTabIndex) {
        pagerState.animateScrollToPage(selectedTabIndex)
    }
    LaunchedEffect(pagerState.currentPage) {//pagerState.isScrollInProgress
        // if(!pagerState.isScrollInProgress){
        selectedTabIndex = pagerState.currentPage
        // }
    }

    Column(
        Modifier.background(
            MaterialTheme.colorScheme.background
        )
    ) {
        TabRow(selectedTabIndex = selectedTabIndex) {
            CharacterViewModel().tabItems.forEachIndexed { index, item ->
                Tab(
                    selected = index == selectedTabIndex,
                    onClick = {
                        selectedTabIndex = index
                    },
                    text = {
                        Text(
                            text = LocalContext.current.getString(item.title)
                        )
                    }
                )
            }
        }
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxSize()
        ) { index ->
            Box(
                contentAlignment = Alignment.TopCenter,
            ) {
                if (index == 0) {
                    Profile(character)
                } else {
                    Profile(character)
                }
            }

        }
    }
}


@Preview(showBackground = true)
@Composable
fun CharacterPreview() {
    CharacterSheetTheme {
        CharacterPage(
            Character(R.string.neuvi, R.drawable.neuvi, R.string.neuvi),
            {}
        )
    }
}