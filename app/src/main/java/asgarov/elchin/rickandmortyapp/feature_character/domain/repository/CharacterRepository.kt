package asgarov.elchin.rickandmortyapp.feature_character.domain.repository

import androidx.paging.PagingData
import asgarov.elchin.rickandmortyapp.feature_character.domain.model.Character
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {

    fun getAllCharacters(): Flow<PagingData<Character>>
}