package asgarov.elchin.rickandmortyapp.feature_character.presentation.character_list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import asgarov.elchin.rickandmortyapp.feature_character.domain.model.Character

@Composable
fun CharacterScreen(navController: NavController){
    val viewModel: CharacterViewModel = hiltViewModel()
    val characters: LazyPagingItems<Character> = viewModel.charactersFlow.collectAsLazyPagingItems()

    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center){

        when (characters.loadState.refresh) {
            is androidx.paging.LoadState.Loading -> {
                CircularProgressIndicator()
            }

            is androidx.paging.LoadState.Error -> {
                val error =
                    (characters.loadState.refresh as androidx.paging.LoadState.Error).error
                Text(
                    text = "Error: ${error.localizedMessage}",
                    color = MaterialTheme.colorScheme.error
                )
            }

            else -> {
                CharacterContent(characters = characters, onItemClick = {})
            }
        }

    }
}