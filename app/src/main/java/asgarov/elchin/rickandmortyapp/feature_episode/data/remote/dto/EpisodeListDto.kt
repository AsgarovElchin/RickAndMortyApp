package asgarov.elchin.rickandmortyapp.feature_episode.data.remote.dto

import asgarov.elchin.rickandmortyapp.core.domain.model.Info

data class EpisodeListDto(
    val info: Info,
    val results: List<EpisodeDto>
)