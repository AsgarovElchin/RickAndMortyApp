package asgarov.elchin.rickandmortyapp.feature_character.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import asgarov.elchin.rickandmortyapp.core.utils.Resource
import asgarov.elchin.rickandmortyapp.feature_character.data.mapper.toCharacter
import asgarov.elchin.rickandmortyapp.feature_character.data.paging.CharacterPagingSource
import asgarov.elchin.rickandmortyapp.feature_character.data.remote.CharacterApi
import asgarov.elchin.rickandmortyapp.feature_character.domain.model.Character
import asgarov.elchin.rickandmortyapp.feature_character.domain.repository.CharacterRepository
import asgarov.elchin.rickandmortyapp.feature_character.domain.use_case.CharacterFilter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val api: CharacterApi
): CharacterRepository {

    override fun getAllCharacters(filter: CharacterFilter): Flow<PagingData<Character>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                prefetchDistance = 2,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {CharacterPagingSource(api,filter)}
        ).flow

    }

    override fun getCharacterById(id: Int): Flow<Resource<Character>> {
        return flow{
            try {
                emit(Resource.Loading())
                val characterDetails = api.getCharacterById(id).toCharacter()
                emit(Resource.Success(characterDetails))

            }catch (e:HttpException){
                emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
            }catch (e:IOException){
                emit(Resource.Error(e.localizedMessage ?: "Couldn't reach server. Check your internet connection"))
            }
        }.flowOn(Dispatchers.IO)
    }
}