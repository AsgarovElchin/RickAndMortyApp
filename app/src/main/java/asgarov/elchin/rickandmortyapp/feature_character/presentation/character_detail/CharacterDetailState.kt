package asgarov.elchin.rickandmortyapp.feature_character.presentation.character_detail

import asgarov.elchin.rickandmortyapp.feature_character.domain.model.Character

data class CharacterDetailState(
    val isLoading:Boolean = false,
    val characterDetail: Character? = null,
    val error:String = ""
)
