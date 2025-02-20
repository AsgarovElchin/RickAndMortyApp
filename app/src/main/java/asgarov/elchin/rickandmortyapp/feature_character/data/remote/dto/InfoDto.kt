package asgarov.elchin.rickandmortyapp.feature_character.data.remote.dto

data class InfoDto(
    val count: Int,
    val next: String?,
    val pages: Int,
    val prev: String?
)