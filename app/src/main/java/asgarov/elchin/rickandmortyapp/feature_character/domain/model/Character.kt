package asgarov.elchin.rickandmortyapp.feature_character.domain.model

data class Character(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val gender: String,
    val origin: Origin,
    val location: Location,
    val image: String,
    val episodeNumbers: List<Int>
)
