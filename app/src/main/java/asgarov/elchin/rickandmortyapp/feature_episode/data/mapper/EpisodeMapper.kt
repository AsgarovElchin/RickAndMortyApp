package asgarov.elchin.rickandmortyapp.feature_episode.data.mapper

import asgarov.elchin.rickandmortyapp.feature_episode.data.remote.dto.EpisodeDto
import asgarov.elchin.rickandmortyapp.feature_episode.domain.model.Episode

fun EpisodeDto.toEpisode():Episode{
    return Episode(
        air_date = air_date,
        characters = characters,
        episode = episode,
        id = id,
        name = name
    )

}