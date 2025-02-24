package asgarov.elchin.rickandmortyapp.feature_character.presentation.character_detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import asgarov.elchin.rickandmortyapp.feature_episode.domain.model.Episode
import asgarov.elchin.rickandmortyapp.feature_episode.presentation.episode_list.components.EpisodeItem

@Composable
fun AppearedEpisodes(
    episodes: List<Episode>,
    onItemClick: (Episode) -> Unit
){


    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(episodes.size) { index ->
                val episode = episodes[index]
                EpisodeItem(
                    episode = episode,
                    onItemClick = onItemClick
                )
            }
        }


    }

}