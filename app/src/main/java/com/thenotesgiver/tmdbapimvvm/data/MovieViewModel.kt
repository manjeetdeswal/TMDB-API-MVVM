package com.thenotesgiver.tmdbapimvvm.data

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.thenotesgiver.tmdbapimvvm.data.model.MovieModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(val repo: MovieRepo) : ViewModel() {


    private var pageNo: Int = 1

    private var nowPlayingResponse :MovieModel? =null

    val getUpcoming = repo.getNowUpcomi().cachedIn(viewModelScope)




    val nowPlayingList = repo.getNowPlaying().cachedIn(viewModelScope)
     fun getNowPlaying(): Flow<MovieModel>  = flow {
        try {

            val result = repo.movieApi.getNowPlaying(pageNo)
            emit(result)
            Log.e("TAG", "getLatest: ${result.toString()}" )
              delay(1000)
         val result1 = repo.movieApi.getNowPlaying(++pageNo)
         emit(result1)
        } catch (e: Exception) {
            e.message.toString()
        }

    }

    private fun handleNowPlaying(response: Response<MovieModel>) {
        if (response.isSuccessful) {
            response.body()?.let { respone ->
                pageNo++
                if (nowPlayingResponse ==null){
                    nowPlayingResponse =respone

                }else{
                    val oldArticles = nowPlayingResponse!!.results
                    val newArticle=respone.results
                    oldArticles.addAll(newArticle)
                }

            }

        }

    }

    suspend fun getPopular(): Flow<MovieModel> = flow {
        try {

            val result = repo.movieApi.getPopular(pageNo)
            emit(result)


        } catch (e: Exception) {
            e.message.toString()
        }

    }



    suspend fun getTopRated(): Flow<MovieModel> = flow {
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

    fun increasePage(){
        ++pageNo

    }

    class MovieFactory(private val rapo: MovieRepo): ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MovieViewModel::class.java)) {
                return MovieViewModel(rapo) as T
            }
            throw IllegalArgumentException("Unkonwn Viewmodel Class")
        }
    }
}