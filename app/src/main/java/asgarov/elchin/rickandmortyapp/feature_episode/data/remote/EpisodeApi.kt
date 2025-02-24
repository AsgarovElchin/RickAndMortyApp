package asgarov.elchin.rickandmortyapp.feature_episode.data.remote

import asgarov.elchin.rickandmortyapp.feature_episode.data.remote.dto.EpisodeDto
import asgarov.elchin.rickandmortyapp.feature_episode.data.remote.dto.EpisodeListDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface EpisodeApi {


    @GET("episode")
    suspend fun getAllEpisodes(
        @Query("page") page: Int,
        @Query("name") name: String? = null
    ):EpisodeListDto

    @GET("episode/{id}")
    suspend fun getEpisodeById(@Path("id")id:Int):EpisodeDto

    @GET("episode/{ids}")
    suspend fun getEpisodesByIds(@Path("ids")ids:String):List<EpisodeDto>



}