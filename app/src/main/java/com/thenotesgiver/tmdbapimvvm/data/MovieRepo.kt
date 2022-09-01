package com.thenotesgiver.tmdbapimvvm.data

import androidx.lifecycle.asLiveData
import androidx.paging.*
import com.thenotesgiver.tmdbapimvvm.data.api.MovieApi
import com.thenotesgiver.tmdbapimvvm.data.model.MovieModel
import com.thenotesgiver.tmdbapimvvm.data.model.MovieResult
import com.thenotesgiver.tmdbapimvvm.data.paging.TMDBPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepo @Inject constructor(val movieApi: MovieApi) {





     fun getNowPlaying()=Pager(
        config = PagingConfig(pageSize = 20 , maxSize = 100),
        pagingSourceFactory = {TMDBPagingSource(movieApi)}
    ).liveData


    fun getNowUpcomi()=Pager(
        config = PagingConfig(pageSize = 20 , maxSize = 80),
        pagingSourceFactory = {TMDBPagingSource(movieApi)}
    ).liveData

    suspend fun getPopular(page:Int):MovieModel = movieApi.getPopular(page)


    suspend fun getTopRated(page:Int):MovieModel = movieApi.getTopRated(page)


    suspend fun getUpcoming(page: Int):MovieModel = movieApi.getUpcoming(page)


}