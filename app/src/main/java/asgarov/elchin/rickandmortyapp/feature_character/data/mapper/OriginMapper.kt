package asgarov.elchin.rickandmortyapp.feature_character.data.mapper

import asgarov.elchin.rickandmortyapp.feature_character.data.remote.dto.OriginDto
import asgarov.elchin.rickandmortyapp.feature_character.domain.model.Origin

fun OriginDto.toOrigin(): Origin {
    return Origin(
        name = name,
        url = url.substringAfterLast("/").toIntOrNull() ?: "")
}