package asgarov.elchin.rickandmortyapp.core.data.dto

data class InfoDto(
    val count: Int,
    val next: String?,
    val pages: Int,
    val prev: String?
)