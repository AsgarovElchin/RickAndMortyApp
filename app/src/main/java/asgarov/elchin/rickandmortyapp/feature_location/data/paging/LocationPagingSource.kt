package asgarov.elchin.rickandmortyapp.feature_location.data.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import asgarov.elchin.rickandmortyapp.feature_location.data.mapper.toLocation
import asgarov.elchin.rickandmortyapp.feature_location.data.remote.LocationApi
import asgarov.elchin.rickandmortyapp.feature_location.domain.model.Location
import asgarov.elchin.rickandmortyapp.feature_location.domain.use_case.LocationFilter
import retrofit2.HttpException
import java.io.IOException

class LocationPagingSource(
    private val api: LocationApi,
    private val filter: LocationFilter?

): PagingSource<Int, Location>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Location> {
        val page = params.key ?: 1
        Log.d("LocationPagingSource", "Loading page: $page")
        return try {
            val response = api.getAllLocation(page = page,
                name = filter?.name
            )
            Log.d("LocationPagingSource", "API response for page $page received with ${response.results.size} items, next: ${response.info.next}")

            val locations = response.results.map { it.toLocation() }
            val prevKey = if (page == 1) null else page - 1
            val nextKey = if (response.info.next == null) null else page + 1

            Log.d("LocationPagingSource", "Returning page: $page, prevKey: $prevKey, nextKey: $nextKey")
            LoadResult.Page(
                data = locations,
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


    override fun getRefreshKey(state: PagingState<Int, Location>): Int? {
        return state.anchorPosition?.let { anchorPosition->
            val closetPage = state.closestPageToPosition(anchorPosition)
            closetPage?.prevKey?.plus(1) ?: closetPage?.nextKey?.minus(1)
        }
    }


}