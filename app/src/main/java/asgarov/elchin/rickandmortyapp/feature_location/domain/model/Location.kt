package asgarov.elchin.rickandmortyapp.feature_location.domain.model

data class Location(
    val dimension: String,
    val id: Int,
    val name: String,
    val residents: List<Any>,
    val type: String
)
