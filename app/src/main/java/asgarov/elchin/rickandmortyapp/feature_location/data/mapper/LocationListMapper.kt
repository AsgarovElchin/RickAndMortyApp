package asgarov.elchin.rickandmortyapp.feature_location.data.mapper

import asgarov.elchin.rickandmortyapp.feature_location.data.remote.dto.LocationListDto
import asgarov.elchin.rickandmortyapp.feature_location.domain.model.LocationList

fun LocationListDto.toLocationList():LocationList{
    return LocationList(
        info = info,
        results = results.map { it.toLocation() }
    )
}