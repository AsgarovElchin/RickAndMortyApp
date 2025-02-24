package asgarov.elchin.rickandmortyapp.feature_episode.presentation.episode_detail

import asgarov.elchin.rickandmortyapp.feature_episode.domain.model.Episode

class EpisodeDetailState(
    val isLoading:Boolean = false,
    val episodeDetail: Episode? = null,
    val error:String = "")
