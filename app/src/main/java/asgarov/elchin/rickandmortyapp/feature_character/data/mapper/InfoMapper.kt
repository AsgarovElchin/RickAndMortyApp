package asgarov.elchin.rickandmortyapp.feature_character.data.mapper

import asgarov.elchin.rickandmortyapp.feature_character.data.remote.dto.InfoDto
import asgarov.elchin.rickandmortyapp.feature_character.domain.model.Info

fun InfoDto.toInfo():Info{
    return Info(
        count = count,
        pages = pages,
        next = next,
        prev = prev
    )
}