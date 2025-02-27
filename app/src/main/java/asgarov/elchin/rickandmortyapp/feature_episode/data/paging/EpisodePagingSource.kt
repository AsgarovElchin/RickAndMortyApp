package asgarov.elchin.rickandmortyapp.feature_episode.data.paging


import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import asgarov.elchin.rickandmortyapp.feature_episode.data.mapper.toEpisode
import asgarov.elchin.rickandmortyapp.feature_episode.data.remote.EpisodeApi
import asgarov.elchin.rickandmortyapp.feature_episode.domain.model.Episode
import asgarov.elchin.rickandmortyapp.feature_episode.domain.use_case.EpisodeFilter
import retrofit2.HttpException
import java.io.IOException

class EpisodePagingSource(
    private val api: EpisodeApi,
    private val filter: EpisodeFilter?

): PagingSource<Int, Episode>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Episode> {
        val page = params.key ?: 1
        Log.d("EpisodePagingSource", "Loading page: $page")
        return try {
            val response = api.getAllEpisodes(page = page,
                name = filter?.name
            )
            Log.d("EpisodePagingSource", "API response for page $page received with ${response.results.size} items, next: ${response.info.next}")

            val episodes = response.results.map { it.toEpisode() }
            val prevKey = if (page == 1) null else page - 1
            val nextKey = if (response.info.next == null) null else page + 1

            Log.d("EpisodePagingSource", "Returning page: $page, prevKey: $prevKey, nextKey: $nextKey")
            LoadResult.Page(
                data = episodes,
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (e: IOException) {
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


    override fun getRefreshKey(state: PagingState<Int, Episode>): Int? {
        return state.anchorPosition?.let { anchorPosition->
            val closetPage = state.closestPageToPosition(anchorPosition)
            closetPage?.prevKey?.plus(1) ?: closetPage?.nextKey?.minus(1)
        }
    }


}