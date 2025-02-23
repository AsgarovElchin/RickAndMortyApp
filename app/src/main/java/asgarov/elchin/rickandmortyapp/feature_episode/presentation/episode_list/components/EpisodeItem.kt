package asgarov.elchin.rickandmortyapp.feature_episode.presentation.episode_list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import asgarov.elchin.rickandmortyapp.feature_episode.domain.model.Episode
import asgarov.elchin.rickandmortyapp.ui.theme.RickAndMortyAppTheme

@Composable
fun EpisodeItem(
    episode: Episode,
    onItemClick: (Episode) -> Unit
){
    Card(
        modifier = Modifier.fillMaxWidth().height(100.dp)
            .clickable { onItemClick(episode) }
            .padding(8.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(6.dp)
    ){
        Row(modifier = Modifier.fillMaxSize().padding(12.dp), verticalAlignment = Alignment.CenterVertically){
            Column(modifier = Modifier.weight(1f)) {
                Text(text = episode.episode,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary)
                Text(text = episode.name,
                    style = MaterialTheme.typography.bodyLarge)
            }
            Text(text = episode.air_date,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant)
        }

    }
}

@Composable
@Preview
fun PreviewEpisodeItem(){
    RickAndMortyAppTheme {
        EpisodeItem(episode = Episode(
            id = 1,
            name = "Pilot",
            air_date = "December 2, 2013",
            episode = "S01E01",
            characters = emptyList()
        ), onItemClick = {})

    }
}