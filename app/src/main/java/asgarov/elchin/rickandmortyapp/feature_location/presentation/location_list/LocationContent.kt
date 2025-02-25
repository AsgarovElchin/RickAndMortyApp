package asgarov.elchin.rickandmortyapp.feature_location.presentation.location_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import asgarov.elchin.rickandmortyapp.feature_location.domain.model.Location
import asgarov.elchin.rickandmortyapp.feature_location.presentation.location_list.components.LocationItem

@Composable
fun LocationContent(
    locations:LazyPagingItems<Location>,
    onItemClick: (Location) -> Unit
){
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(locations.itemCount) { index ->
                val location = locations[index]
                if (location != null) {
                    LocationItem(
                        location = location,
                        onItemClick = onItemClick
                    )
                }
            }
        }


        if (locations.loadState.append is LoadState.Loading) {
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