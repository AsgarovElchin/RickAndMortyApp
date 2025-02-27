package asgarov.elchin.rickandmortyapp.feature_character.domain.model

import asgarov.elchin.rickandmortyapp.core.domain.model.Info

data class CharacterList(
    val info: Info,
    val characters: List<Character>
)
