package asgarov.elchin.rickandmortyapp.feature_location.data.remote

import asgarov.elchin.rickandmortyapp.feature_location.data.remote.dto.LocationDto
import asgarov.elchin.rickandmortyapp.feature_location.data.remote.dto.LocationListDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface LocationApi {

    @GET("location")
    suspend fun getAllLocation(
        @Query("page") page: Int,
        @Query("name") name: String? = null
    ):LocationListDto

    @GET("location/{id}")
    suspend fun getLocationById(@Path("id")id:Int):LocationDto
}