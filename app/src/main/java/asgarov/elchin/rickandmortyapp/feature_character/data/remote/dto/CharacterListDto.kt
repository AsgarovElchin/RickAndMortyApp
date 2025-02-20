package asgarov.elchin.rickandmortyapp.feature_character.data.remote.dto

data class CharacterListDto(
    val info: InfoDto,
    val results: List<CharacterDto>
)