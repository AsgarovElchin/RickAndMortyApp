package asgarov.elchin.rickandmortyapp.feature_episode.domain.use_case

import androidx.paging.PagingData
import asgarov.elchin.rickandmortyapp.feature_episode.domain.model.Episode
import asgarov.elchin.rickandmortyapp.feature_episode.domain.repository.EpisodeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetEpisodesUseCase @Inject constructor(
    private val repository: EpisodeRepository
){
    operator fun invoke(filter: EpisodeFilter): Flow<PagingData<Episode>> {
        return repository.getAllEpisodes(filter)
    }
}