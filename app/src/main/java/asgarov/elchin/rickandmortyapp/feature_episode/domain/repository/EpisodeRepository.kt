package asgarov.elchin.rickandmortyapp.feature_episode.domain.repository

import androidx.paging.PagingData
import asgarov.elchin.rickandmortyapp.core.utils.Resource
import asgarov.elchin.rickandmortyapp.feature_episode.domain.model.Episode
import asgarov.elchin.rickandmortyapp.feature_episode.domain.use_case.EpisodeFilter
import kotlinx.coroutines.flow.Flow

interface EpisodeRepository {

    fun getAllEpisodes(filter: EpisodeFilter): Flow<PagingData<Episode>>

    fun getEpisodeById(id: Int): Flow<Resource<Episode>>


}