package com.thenotesgiver.tmdbapimvvm.data

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thenotesgiver.tmdbapimvvm.data.model.MovieModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(val repo: MovieRepo) : ViewModel() {




    fun getNowPlaying(pageNo :Int): Flow<MovieModel>  = flow {

            try {

                val result = repo.movieApi.getNowPlaying(pageNo)
                emit(result)

            } catch (e: Exception) {
                e.message.toString()
            }



    }



    suspend fun getPopular(pageNo :Int): Flow<MovieModel> = flow {

            try {

                val result = repo.movieApi.getPopular(pageNo)
                emit(result)


            } catch (e: Exception) {
                e.message.toString()
            }



    }



    suspend fun getTopRated(pageNo :Int): Flow<MovieModel> = flow {

            try {

                val result = repo.movieApi.getTopRated(pageNo)
                emit(result)
            } catch (e: Exception) {
                e.message.toString()
            }



    }


    suspend fun getUpcoming(pageNO:Int): Flow<MovieModel>  = flow {


            try {

                val result = repo.movieApi.getUpcoming(pageNO)
                emit(result)
                Log.e("TAG", "getLatest: ${result.toString()}" )



            } catch (e: Exception) {
                e.message.toString()
            }



    }






}