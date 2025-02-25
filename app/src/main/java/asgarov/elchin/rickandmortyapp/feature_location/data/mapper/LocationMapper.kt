package asgarov.elchin.rickandmortyapp.feature_location.data.mapper

import asgarov.elchin.rickandmortyapp.feature_location.data.remote.dto.LocationDto
import asgarov.elchin.rickandmortyapp.feature_location.domain.model.Location

fun LocationDto.toLocation():Location{
    return Location(
        dimension = dimension,
        id = id,
        name = name,
        residents = residents.map { it.substringAfterLast("/").toIntOrNull()?: "" },
        type = type
    )
}
