package asgarov.elchin.rickandmortyapp.feature_episode.domain.model

import asgarov.elchin.rickandmortyapp.core.domain.model.Info

data class EpisodeList(
    val info: Info,
    val episodes:List<Episode>
)
