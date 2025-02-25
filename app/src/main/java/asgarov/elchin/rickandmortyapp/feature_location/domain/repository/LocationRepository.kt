package asgarov.elchin.rickandmortyapp.feature_location.domain.repository

import androidx.paging.PagingData
import asgarov.elchin.rickandmortyapp.core.utils.Resource
import asgarov.elchin.rickandmortyapp.feature_location.domain.model.Location
import asgarov.elchin.rickandmortyapp.feature_location.domain.use_case.LocationFilter
import kotlinx.coroutines.flow.Flow

interface LocationRepository {

    fun getAllLocations(filter: LocationFilter): Flow<PagingData<Location>>

    fun getLocationById(id: Int): Flow<Resource<Location>>

}