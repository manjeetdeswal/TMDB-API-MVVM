package com.thenotesgiver.tmdbapimvvm.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.thenotesgiver.tmdbapimvvm.data.api.MovieApi
import com.thenotesgiver.tmdbapimvvm.data.model.MovieResult

class TMDBPagingSource(private val api : MovieApi) : PagingSource<Int, MovieResult>() {


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieResult> {
        return try {
            val postion = params.key ?: 1
            val response = api.getUpcoming(postion)

            return LoadResult.Page(
                data = response.results,
                prevKey = if (postion ==1) null else postion -1,
                nextKey = if (postion == response.total_pages) null else postion + 1
            )

        }catch (e:Exception){
           LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, MovieResult>): Int? {
        return state.anchorPosition ?.let {
            val anchorPage = state.closestPageToPosition(it)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }

    }
}