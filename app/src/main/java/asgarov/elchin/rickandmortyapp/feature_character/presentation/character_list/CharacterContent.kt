package asgarov.elchin.rickandmortyapp.feature_character.presentation.character_list

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import asgarov.elchin.rickandmortyapp.feature_character.domain.model.Character
import asgarov.elchin.rickandmortyapp.feature_character.presentation.character_list.components.CharacterItem
import kotlinx.coroutines.flow.distinctUntilChanged



@Composable
fun CharacterContent(
    characters: LazyPagingItems<Character>,
    onItemClick: (Character) -> Unit
) {

    val loggedIds = remember { mutableSetOf<Int>() }

    LaunchedEffect(characters) {
        snapshotFlow { characters.itemSnapshotList }
            .distinctUntilChanged()
            .collect { snapshot ->
                snapshot.filterNotNull().forEach { character ->
                    if (loggedIds.add(character.id)) {
                        Log.d("CharacterLog", "Character: $character")
                    }
                }
            }
    }


    Box(modifier = Modifier.fillMaxSize()) {
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
        }


        if (characters.loadState.append is LoadState.Loading) {
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

