package asgarov.elchin.rickandmortyapp.feature_episode.presentation.episode_detail

import asgarov.elchin.rickandmortyapp.feature_episode.domain.model.Episode

data class EpisodesDetailState(
    val episodesDetail: List<Episode>? = emptyList(),
    val isLoading: Boolean = false,
    val error: String = ""
)
