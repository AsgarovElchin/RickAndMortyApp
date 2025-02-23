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
import asgarov.elchin.rickandmortyapp.feature_character.presentation.character_list.components.FilterBar
import asgarov.elchin.rickandmortyapp.feature_character.presentation.character_list.components.FilterDialog

@Composable
fun CharacterScreen(navController: NavController) {
    val viewModel: CharacterViewModel = hiltViewModel()
    val filter by viewModel.filterState.collectAsState()
    val characters = viewModel.charactersFlow.collectAsLazyPagingItems()



    var showAdvancedFilters by remember { mutableStateOf(false) }

    val filterOptions = mapOf(
        "Status" to listOf("Alive", "Dead", "Unknown"),
        "Species" to listOf("Alien", "Animal", "Human", "Humanoid", "Mythological Creature", "Poopybutthole", "Robot", "Unknown"),
        "Gender" to listOf("Female", "Genderless", "Male", "Unknown")
    )

    val currentFilterValues = mapOf(
        "Status" to filter.status.orEmpty(),
        "Species" to filter.species.orEmpty(),
        "Gender" to filter.gender.orEmpty()
    )

    Column(modifier = Modifier.fillMaxSize()) {
        FilterBar(
            searchQuery = filter.name.orEmpty(),
            labelText = "Character Name",
            onSearchQueryChange = { newName ->
                viewModel.updateFilter(filter.copy(name = newName))
            },
            onFilterClick = { showAdvancedFilters = true }
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
        FilterDialog(
            title = "Advanced Filters",
            filterOptions = filterOptions,
            currentFilterValues = currentFilterValues,
            onDismiss = { showAdvancedFilters = false },
            onApply = { newFilter ->
                viewModel.updateFilter(
                    filter.copy(
                        status = newFilter["Status"],
                        species = newFilter["Species"],
                        gender = newFilter["Gender"]
                    )
                )
                showAdvancedFilters = false
            }
        )
    }
}