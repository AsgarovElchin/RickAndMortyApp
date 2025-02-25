package asgarov.elchin.rickandmortyapp.feature_location.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import asgarov.elchin.rickandmortyapp.core.utils.Resource
import asgarov.elchin.rickandmortyapp.feature_location.data.mapper.toLocation
import asgarov.elchin.rickandmortyapp.feature_location.data.paging.LocationPagingSource
import asgarov.elchin.rickandmortyapp.feature_location.data.remote.LocationApi
import asgarov.elchin.rickandmortyapp.feature_location.domain.model.Location
import asgarov.elchin.rickandmortyapp.feature_location.domain.repository.LocationRepository
import asgarov.elchin.rickandmortyapp.feature_location.domain.use_case.LocationFilter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class LocationRepositoryImpl @Inject constructor(
    private val api: LocationApi
):LocationRepository {
    override fun getAllLocations(filter: LocationFilter): Flow<PagingData<Location>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                prefetchDistance = 2,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { LocationPagingSource(api,filter) }
        ).flow
    }

    override fun getLocationById(id: Int): Flow<Resource<Location>> {
        return flow{
            try {
                emit(Resource.Loading())
                val locationDetails = api.getLocationById(id).toLocation()
                emit(Resource.Success(locationDetails))

            }catch (e: HttpException){
                emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
            }catch (e: IOException){
                emit(Resource.Error(e.localizedMessage ?: "Couldn't reach server. Check your internet connection"))
            }
        }.flowOn(Dispatchers.IO)
    }
}