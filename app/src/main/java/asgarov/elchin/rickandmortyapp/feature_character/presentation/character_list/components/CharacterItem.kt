package asgarov.elchin.rickandmortyapp.feature_character.presentation.character_list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import asgarov.elchin.rickandmortyapp.feature_character.domain.model.Character
import coil3.compose.SubcomposeAsyncImage

@Composable
fun CharacterItem(
    character: Character,
    onItemClick: (Character) -> Unit
){
    Card(
        modifier = Modifier.fillMaxWidth()
            .clickable { onItemClick(character) }
            .padding(8.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(6.dp)
    ){
        Row(
            modifier = Modifier.padding(8.dp)
        ) {
            SubcomposeAsyncImage(
                model = character.image,
                contentDescription = "Character's image",
                modifier = Modifier.size(100.dp)
                    .padding(end = 8.dp),
                contentScale = ContentScale.Crop,
                loading = { CircularProgressIndicator() }
            )
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = character.name,
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
                )
                Text(
                    text = "${character.status} - ${character.species}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Last known location:",
                    style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold)
                )
                Text(
                    text = character.location.name,
                    style = MaterialTheme.typography.bodyMedium
                )

            }


        }


    }


}

