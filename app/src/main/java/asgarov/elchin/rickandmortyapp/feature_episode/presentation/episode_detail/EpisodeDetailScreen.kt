package asgarov.elchin.rickandmortyapp.feature_episode.presentation.episode_detail

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import asgarov.elchin.rickandmortyapp.core.presentation.CharacterListDialog



@Composable
fun EpisodeDetailScreen(
    navController: NavController
) {
    val viewModel: EpisodeDetailViewModel = hiltViewModel()
    val characterState = viewModel.charactersDetailState.value

    val showDialog = rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(characterState.charactersDetail) {
        if (!characterState.isLoading && characterState.charactersDetail?.isNotEmpty() == true) {
            showDialog.value = true
        }
    }


    if (characterState.isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }


    if (characterState.error.isNotEmpty()) {
        Text(
            text = "Error: ${characterState.error}",
            color = MaterialTheme.colorScheme.error,
            modifier = Modifier.padding(16.dp)
        )
    }

    if (showDialog.value) {
        CharacterListDialog(
            title = "Episode Characters",
            characters = characterState.charactersDetail ?: emptyList(),
            onDismiss = {
                showDialog.value = false
                navController.popBackStack()
            },
            navController
        )
    }

    BackHandler(enabled = showDialog.value) {
        showDialog.value = false
        navController.popBackStack()
    }
}