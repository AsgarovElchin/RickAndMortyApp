package asgarov.elchin.rickandmortyapp.feature_location.domain.use_case

import androidx.paging.PagingData
import asgarov.elchin.rickandmortyapp.feature_location.domain.model.Location
import asgarov.elchin.rickandmortyapp.feature_location.domain.repository.LocationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLocationsUseCase @Inject constructor(
    private val repository: LocationRepository
) {

    operator fun invoke(filter: LocationFilter): Flow<PagingData<Location>> {
        return repository.getAllLocations(filter)
    }
}