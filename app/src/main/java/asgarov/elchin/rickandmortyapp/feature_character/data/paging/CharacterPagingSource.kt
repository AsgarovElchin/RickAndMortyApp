package asgarov.elchin.rickandmortyapp.feature_character.data.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import asgarov.elchin.rickandmortyapp.feature_character.data.mapper.toCharacter
import asgarov.elchin.rickandmortyapp.feature_character.data.remote.CharacterApi
import asgarov.elchin.rickandmortyapp.feature_character.domain.model.Character
import asgarov.elchin.rickandmortyapp.feature_character.domain.use_case.CharacterFilter
import okio.IOException
import retrofit2.HttpException

class CharacterPagingSource(
    private val api:CharacterApi,
    private val filter: CharacterFilter
):PagingSource<Int,Character>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        val page = params.key ?: 1
        Log.d("CharacterPagingSource", "Loading page: $page")
        return try {
            val response = api.getAllCharacters(page = page,
                name = filter.name,
                status = filter.status,
                species = filter.species,
                gender = filter.gender
            )

            Log.d("CharacterPagingSource", "API response for page $page received with ${response.results.size} items, next: ${response.info.next}")

            val characters = response.results.map { it.toCharacter() }
            val prevKey = if (page == 1) null else page - 1
            val nextKey = if (response.info.next == null) null else page + 1

            Log.d("CharacterPagingSource", "Returning page: $page, prevKey: $prevKey, nextKey: $nextKey")
            LoadResult.Page(
                data = characters,
                prevKey = prevKey,
                nextKey = nextKey
            )

        }
        catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            val errorMessage = when (e.code()) {
                400 -> "Bad request. Please try again."
                404 -> "There is nothing here."
                500 -> "Server is currently unavailable. Please try again later."
                else -> "Unexpected API error."
            }
            LoadResult.Error(Exception(errorMessage))
        }
    }


    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return state.anchorPosition?.let { anchorPosition->
            val closetPage = state.closestPageToPosition(anchorPosition)
            closetPage?.prevKey?.plus(1) ?: closetPage?.nextKey?.minus(1)
        }
    }


}









