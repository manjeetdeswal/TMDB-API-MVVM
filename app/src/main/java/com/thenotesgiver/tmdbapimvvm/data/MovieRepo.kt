package com.thenotesgiver.tmdbapimvvm.data

import android.util.Log
import androidx.lifecycle.asLiveData
import androidx.paging.*
import com.thenotesgiver.tmdbapimvvm.data.api.MovieApi
import com.thenotesgiver.tmdbapimvvm.data.model.MovieModel
import com.thenotesgiver.tmdbapimvvm.data.model.MovieResult
import com.thenotesgiver.tmdbapimvvm.data.paging.TMDBPagingSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieRepo @Inject constructor(val movieApi: MovieApi) {






   suspend fun getNowPlaying(page:Int):MovieModel = movieApi.getNowPlaying(page)




    suspend fun getPopular(page:Int):MovieModel = movieApi.getPopular(page)


    suspend fun getTopRated(page:Int):MovieModel = movieApi.getTopRated(page)


    suspend fun getUpcoming(page: Int):MovieModel = movieApi.getUpcoming(page)


}