package asgarov.elchin.rickandmortyapp.feature_location.data.remote.dto

import asgarov.elchin.rickandmortyapp.core.domain.model.Info

data class LocationListDto(
    val info: Info,
    val results: List<LocationDto>
)