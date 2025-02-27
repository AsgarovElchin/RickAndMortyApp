package asgarov.elchin.rickandmortyapp.feature_character.data.remote.dto

import asgarov.elchin.rickandmortyapp.core.data.dto.InfoDto

data class CharacterListDto(
    val info: InfoDto,
    val results: List<CharacterDto>
)