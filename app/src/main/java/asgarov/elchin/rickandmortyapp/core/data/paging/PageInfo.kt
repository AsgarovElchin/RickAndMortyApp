package asgarov.elchin.rickandmortyapp.core.data.paging

data class PageInfo(
    val count: Int,
    val pages: Int,
    val next: String?,
    val prev: String?
)