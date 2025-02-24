package asgarov.elchin.rickandmortyapp.feature_episode.presentation.episode_list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import asgarov.elchin.rickandmortyapp.EpisodeDetailRoute
import asgarov.elchin.rickandmortyapp.feature_character.presentation.character_list.components.FilterBar


@Composable
fun EpisodeScreen(navController: NavController){
    val viewModel: EpisodeViewModel = hiltViewModel()
    val filter by viewModel.filterState.collectAsState()
    val episodes = viewModel.episodesFlow.collectAsLazyPagingItems()



    Column(modifier = Modifier.fillMaxSize()) {
        FilterBar(
            searchQuery = filter.name.orEmpty(),
            labelText = "Episode Name",
            onSearchQueryChange = { newQuery ->
                viewModel.updateFilter(filter.copy(name = newQuery))
            },
        )

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            when (episodes.loadState.refresh) {
                is androidx.paging.LoadState.Loading -> {
                    CircularProgressIndicator()
                }
                is androidx.paging.LoadState.Error -> {
                    val error =
                        (episodes.loadState.refresh as androidx.paging.LoadState.Error).error
                    Text(
                        text = "Error: ${error.localizedMessage}",
                        color = MaterialTheme.colorScheme.error
                    )
                }
                else -> {
                    EpisodeContent(episodes = episodes, onItemClick = { episode->
                        navController.navigate(EpisodeDetailRoute(episode.id))
                    })
                }
            }
        }
    }


}