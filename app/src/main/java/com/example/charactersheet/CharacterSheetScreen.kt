package com.example.charactersheet

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.charactersheet.ui.ArtistList
import com.example.charactersheet.ui.CharacterPage
import com.example.charactersheet.ui.CharacterViewModel
import com.example.charactersheet.ui.ChooseCharacter

enum class CharacterScreen(@StringRes var title: Int) {
    Start(title = R.string.app_name),
    Artist(title = R.string.app_name),
    Character(title = R.string.empty)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(
    currentScreen: CharacterScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier) {
    if(currentScreen.title != CharacterScreen.Character.title) {
        CenterAlignedTopAppBar(
            navigationIcon = {
                if (canNavigateBack) {
                    IconButton(onClick = navigateUp) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.previous_button)
                        )
                    }
                }
            },
            title = {
                Text(
                    stringResource(currentScreen.title),
                    style = MaterialTheme.typography.titleLarge
                )
            },
            modifier = modifier,
        )
    }
}
@Composable
fun CharacterSheetApp(
    viewModel: CharacterViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
){
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = CharacterScreen.valueOf(
        backStackEntry?.destination?.route ?: CharacterScreen.Start.name
    )
    Scaffold(
        topBar = {
            TopAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ){ innerPadding ->
        val uiState by viewModel.uiState.collectAsState()

        NavHost(
            navController = navController,
            startDestination = CharacterScreen.Start.name,
            modifier = Modifier.padding(innerPadding)
        ){
            composable(route = CharacterScreen.Start.name) {
                ArtistList(
                    onButtonCard = {
                        CharacterScreen.Artist.title = it.stringResourceId
                        viewModel.selectedArtist(it)
                        navController.navigate(CharacterScreen.Artist.name)
                    },
                    uiState.listOfArtists,
                )
            }
            composable(route = CharacterScreen.Artist.name) {
                ChooseCharacter(
                    onButtonCard = {
                        viewModel.selectedCharacter(it)
                        navController.navigate(CharacterScreen.Character.name)
                    },
                    uiState.listOfCharacters
                )
            }
            composable(route = CharacterScreen.Character.name) {
                CharacterPage(
                    uiState.characterChosen,
                    navigateUp = { navController.navigateUp() }
                )
            }
        }
    }
}