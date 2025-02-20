package asgarov.elchin.rickandmortyapp.feature_character.data.remote.dto

data class CharacterDto(
    val info: Info,
    val results: List<Result>
)