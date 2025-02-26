package asgarov.elchin.rickandmortyapp.feature_character.presentation.character_detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import asgarov.elchin.rickandmortyapp.core.navigation.AppRoute


@Composable
fun CharacterDetailScreen(
    navController: NavController
) {
    val viewModel: CharacterDetailViewModel = hiltViewModel()
    val characterDetailState = viewModel.characterDetailState.value
    val episodesDetailState = viewModel.episodesDetailState.value

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        when {
            characterDetailState.isLoading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
            characterDetailState.error.isNotEmpty() -> {
                Text(
                    text = "Error: ${characterDetailState.error}",
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(16.dp)
                )
            }
            characterDetailState.characterDetail != null -> {
                val character = characterDetailState.characterDetail


                CharacterDetailContent(character = character)

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    modifier = Modifier.padding(start = 16.dp),
                    text = "Appeared in Episodes:",
                    style = MaterialTheme.typography.headlineSmall
                )

                if (episodesDetailState.isLoading) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                } else if (episodesDetailState.error.isNotEmpty()) {
                    Text(
                        text = "Error: ${episodesDetailState.error}",
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.padding(16.dp)
                    )
                } else {
                    episodesDetailState.episodesDetail?.let { episodes ->
                        AppearedEpisodes(
                            episodes = episodes,
                            onItemClick = { episode ->
                                navController.navigate(AppRoute.EpisodeDetailRoute(episode.id))
                            }
                        )
                    }
                }
            }
        }
    }
}
