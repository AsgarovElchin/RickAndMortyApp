package asgarov.elchin.rickandmortyapp.feature_location.presentation.location_list.components

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
import asgarov.elchin.rickandmortyapp.LocationDetailRoute
import asgarov.elchin.rickandmortyapp.feature_character.presentation.character_list.components.FilterBar
import asgarov.elchin.rickandmortyapp.feature_location.presentation.location_list.LocationContent
import asgarov.elchin.rickandmortyapp.feature_location.presentation.location_list.LocationViewModel

@Composable
fun LocationScreen(navController: NavController){
    val viewModel: LocationViewModel = hiltViewModel()
    val filter by viewModel.filterState.collectAsState()
    val locations = viewModel.locationsFlow.collectAsLazyPagingItems()

    Column(modifier = Modifier.fillMaxSize()) {
        FilterBar(
            searchQuery = filter.name.orEmpty(),
            labelText = "Location Name",
            onSearchQueryChange = { newQuery ->
                viewModel.updateFilter(filter.copy(name = newQuery))
            },
        )

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            when (locations.loadState.refresh) {
                is androidx.paging.LoadState.Loading -> {
                    CircularProgressIndicator()
                }
                is androidx.paging.LoadState.Error -> {
                    val error =
                        (locations.loadState.refresh as androidx.paging.LoadState.Error).error
                    Text(
                        text = "Error: ${error.localizedMessage}",
                        color = MaterialTheme.colorScheme.error
                    )
                }
                else -> {
                    LocationContent(locations = locations, onItemClick = { location->
                        navController.navigate(LocationDetailRoute(location.id))
                    })
                }
            }
        }
    }

}