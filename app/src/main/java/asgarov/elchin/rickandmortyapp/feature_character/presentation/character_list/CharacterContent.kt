package asgarov.elchin.rickandmortyapp.feature_character.presentation.character_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import asgarov.elchin.rickandmortyapp.feature_character.domain.model.Character
import asgarov.elchin.rickandmortyapp.feature_character.presentation.character_list.components.CharacterItem

@Composable
fun CharacterContent(
    characters: LazyPagingItems<Character>,
    onItemClick: (Character) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(characters.itemCount) { index ->
            val character = characters[index]
            if (character != null) {
                CharacterItem(
                    character = character,
                    onItemClick = onItemClick
                )
            }
        }

        characters.apply {
            when (loadState.append) {
                is LoadState.Loading -> {
                    item { CircularProgressIndicator(modifier = Modifier.fillMaxWidth().padding(16.dp)) }
                }

                is LoadState.Error -> {
                    val error = loadState.append as LoadState.Error
                    item { Text(text = "Error: ${error.error.localizedMessage}") }
                }

                is LoadState.NotLoading -> {}
            }
        }

    }
}
