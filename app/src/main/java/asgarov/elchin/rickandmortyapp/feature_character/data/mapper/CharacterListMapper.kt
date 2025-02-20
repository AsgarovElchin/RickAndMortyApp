package asgarov.elchin.rickandmortyapp.feature_character.data.mapper

import asgarov.elchin.rickandmortyapp.feature_character.data.remote.dto.CharacterListDto
import asgarov.elchin.rickandmortyapp.feature_character.domain.model.CharacterList

fun CharacterListDto.toCharacterList():CharacterList{
    return CharacterList(
        info = info.toInfo(),
        characters = results.map { it.toCharacter() }

    )
}