package asgarov.elchin.rickandmortyapp.feature_character.presentation.character_detail

import asgarov.elchin.rickandmortyapp.feature_character.domain.model.Character

data class CharactersDetailState(
    val isLoading:Boolean = false,
    val charactersDetail: List<Character>? = emptyList(),
    val error:String = ""
)
