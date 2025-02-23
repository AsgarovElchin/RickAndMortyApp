package asgarov.elchin.rickandmortyapp.feature_episode.data.remote.dto

data class EpisodeDto(
    val air_date: String,
    val characters: List<String>,
    val created: String,
    val episode: String,
    val id: Int,
    val name: String,
    val url: String
)