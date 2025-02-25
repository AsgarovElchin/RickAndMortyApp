package asgarov.elchin.rickandmortyapp.feature_episode.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import asgarov.elchin.rickandmortyapp.core.utils.Resource
import asgarov.elchin.rickandmortyapp.feature_episode.data.mapper.toEpisode
import asgarov.elchin.rickandmortyapp.feature_episode.data.paging.EpisodePagingSource
import asgarov.elchin.rickandmortyapp.feature_episode.data.remote.EpisodeApi
import asgarov.elchin.rickandmortyapp.feature_episode.domain.model.Episode
import asgarov.elchin.rickandmortyapp.feature_episode.domain.repository.EpisodeRepository
import asgarov.elchin.rickandmortyapp.feature_episode.domain.use_case.EpisodeFilter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class EpisodeRepositoryImpl @Inject constructor(
    private val api:EpisodeApi
):EpisodeRepository {
    override fun getAllEpisodes(filter: EpisodeFilter): Flow<PagingData<Episode>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                prefetchDistance = 2,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { EpisodePagingSource(api,filter) }
        ).flow
    }

    override fun getEpisodeById(id: Int): Flow<Resource<Episode>> {
        return flow{
            try {
                emit(Resource.Loading())
                val episodeDetails = api.getEpisodeById(id).toEpisode()
                emit(Resource.Success(episodeDetails))

            }catch (e: HttpException){
                emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
            }catch (e: IOException){
                emit(Resource.Error(e.localizedMessage ?: "Couldn't reach server. Check your internet connection"))
            }
        }.flowOn(Dispatchers.IO)
    }

    override fun getEpisodesByIds(ids: String): Flow<Resource<List<Episode>>> {
        return flow{
            try {
                emit(Resource.Loading())
                val episodesDetails = api.getEpisodesByIds(ids).map { it.toEpisode() }
                emit(Resource.Success(episodesDetails))

            }catch (e: HttpException){
                emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
            }catch (e: IOException){
                emit(Resource.Error(e.localizedMessage ?: "Couldn't reach server. Check your internet connection"))
            }
        }.flowOn(Dispatchers.IO)
    }


}