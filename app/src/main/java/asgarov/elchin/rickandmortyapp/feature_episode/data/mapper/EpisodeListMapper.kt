package asgarov.elchin.rickandmortyapp.feature_episode.data.mapper

import asgarov.elchin.rickandmortyapp.feature_episode.data.remote.dto.EpisodeListDto
import asgarov.elchin.rickandmortyapp.feature_episode.domain.model.EpisodeList

fun EpisodeListDto.toEpisodeList():EpisodeList{
    return EpisodeList(
        info = info,
        episodes = results.map { it.toEpisode() }
    )
}