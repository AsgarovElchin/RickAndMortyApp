package asgarov.elchin.rickandmortyapp.feature_location.data.remote.dto

data class LocationDto(
    val created: String,
    val dimension: String,
    val id: Int,
    val name: String,
    val residents: List<String>,
    val type: String,
    val url: String
)