package asgarov.elchin.rickandmortyapp.feature_episode.presentation.episode_list

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import asgarov.elchin.rickandmortyapp.feature_character.presentation.character_list.components.CharacterItem
import asgarov.elchin.rickandmortyapp.feature_episode.domain.model.Episode
import asgarov.elchin.rickandmortyapp.feature_episode.presentation.episode_list.components.EpisodeItem
import kotlinx.coroutines.flow.distinctUntilChanged

@Composable
fun EpisodeContent(
    episodes: LazyPagingItems<Episode>,
    onItemClick: (Episode) -> Unit
){

    val loggedIds = remember { mutableSetOf<Int>() }

    LaunchedEffect(episodes) {
        snapshotFlow { episodes.itemSnapshotList }
            .distinctUntilChanged()
            .collect { snapshot ->
                snapshot.filterNotNull().forEach { episode ->
                    if (loggedIds.add(episode.id)) {
                        Log.d("EpisodeLog", "Episode: $episode")
                    }
                }
            }
    }


    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(episodes.itemCount) { index ->
                val episode = episodes[index]
                if (episode != null) {
                    EpisodeItem(
                        episode = episode,
                        onItemClick = onItemClick
                    )
                }
            }
        }


        if (episodes.loadState.append is LoadState.Loading) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
                    .align(Alignment.BottomCenter),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(modifier = Modifier.size(24.dp))
            }
        }
    }

}