package asgarov.elchin.rickandmortyapp.feature_character.domain.repository

import androidx.paging.PagingData
import asgarov.elchin.rickandmortyapp.core.utils.Resource
import asgarov.elchin.rickandmortyapp.feature_character.domain.model.Character
import asgarov.elchin.rickandmortyapp.feature_character.domain.use_case.CharacterFilter
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {

    fun getAllCharacters(filter: CharacterFilter): Flow<PagingData<Character>>

    fun getCharacterById(id: Int): Flow<Resource<Character>>

    fun getCharactersByIds(ids:String):Flow<Resource<List<Character>>>
}