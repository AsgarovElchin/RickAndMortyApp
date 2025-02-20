package asgarov.elchin.rickandmortyapp.feature_character.data.mapper

import asgarov.elchin.rickandmortyapp.feature_character.data.remote.dto.CharacterDto
import asgarov.elchin.rickandmortyapp.feature_character.domain.model.Character

fun CharacterDto.toCharacter():Character{
    return Character(
        id = id,
        name = name,
        status = status,
        species = species,
        gender = gender,
        origin = origin.toOrigin(),
        location = location.toLocation(),
        image = image,
        episodeNumbers = episode.map { it.substringAfter("/").toInt() }

    )
}