package asgarov.elchin.rickandmortyapp.feature_character.data.mapper

import asgarov.elchin.rickandmortyapp.feature_character.data.remote.dto.LocationDto
import asgarov.elchin.rickandmortyapp.feature_character.domain.model.Location

fun LocationDto.toLocation():Location{
    return Location(
        name = name,
        url = url.substringAfter("/").toInt()
    )
}