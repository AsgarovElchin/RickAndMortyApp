package asgarov.elchin.rickandmortyapp.feature_character.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import asgarov.elchin.rickandmortyapp.feature_character.data.paging.CharacterPagingSource
import asgarov.elchin.rickandmortyapp.feature_character.data.remote.CharacterApi
import asgarov.elchin.rickandmortyapp.feature_character.domain.model.Character
import asgarov.elchin.rickandmortyapp.feature_character.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val api: CharacterApi
): CharacterRepository {

    override fun getAllCharacters(): Flow<PagingData<Character>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                prefetchDistance = 2,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {CharacterPagingSource(api)}
        ).flow

    }
}