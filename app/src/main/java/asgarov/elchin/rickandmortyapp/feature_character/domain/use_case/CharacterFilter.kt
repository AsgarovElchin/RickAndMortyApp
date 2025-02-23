package asgarov.elchin.rickandmortyapp.feature_character.domain.use_case

data class CharacterFilter(
    val name: String? = "",
    val status: String? = "",
    val species: String? = "",
    val gender: String? = ""
)
