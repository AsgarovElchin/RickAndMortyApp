package asgarov.elchin.rickandmortyapp.feature_character.presentation.character_list

import asgarov.elchin.rickandmortyapp.feature_character.domain.model.Character

data class CharacterState(
    val isLoading: Boolean = false,
    val characters: List<Character> = emptyList(),
    val error: String = ""

)


