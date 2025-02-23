package asgarov.elchin.rickandmortyapp.feature_character.presentation.character_list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import asgarov.elchin.rickandmortyapp.feature_character.presentation.character_list.components.AdvancedFilterDialog
import asgarov.elchin.rickandmortyapp.feature_character.presentation.character_list.components.FilterBar

@Composable
fun CharacterScreen(navController: NavController) {
    val viewModel: CharacterViewModel = hiltViewModel()
    val filter by viewModel.filterState.collectAsState()
    val characters = viewModel.charactersFlow.collectAsLazyPagingItems()


    var showAdvancedFilters by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxSize()) {
        FilterBar(
            currentFilter = filter,
            onNameFilterChange = { newName ->
                viewModel.updateFilter(filter.copy(name = newName))
            },
            onShowAdvancedFilters = { showAdvancedFilters = true }
        )

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
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
                    CharacterContent(characters = characters, onItemClick = { /* Handle click */ })
                }
            }
        }
    }

    if (showAdvancedFilters) {
        AdvancedFilterDialog(
            currentFilter = filter,
            onDismiss = { showAdvancedFilters = false },
            onApply = { newFilter ->
                viewModel.updateFilter(newFilter)
                showAdvancedFilters = false
            }
        )
    }
}