package asgarov.elchin.rickandmortyapp.core.data.paging



data class PaginatedResponse<T>(
    val info: PageInfo,
    val results: List<T>
)
