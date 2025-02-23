package asgarov.elchin.rickandmortyapp.feature_episode.domain.model

data class Episode(
    val air_date: String,
    val characters: List<String>,
    val episode: String,
    val id: Int,
    val name: String
)
