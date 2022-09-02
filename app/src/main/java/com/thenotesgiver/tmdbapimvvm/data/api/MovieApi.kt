package com.thenotesgiver.tmdbapimvvm.data.api

import com.thenotesgiver.tmdbapimvvm.data.model.MovieModel
import com.thenotesgiver.tmdbapimvvm.helper.Constant
import com.thenotesgiver.tmdbapimvvm.helper.Constant.API_KEY
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {




    @GET("/3/movie/now_playing?api_key=$API_KEY")
    suspend fun getNowPlaying(@Query("page") page: Int):MovieModel

    @GET("/3/movie/popular?api_key=$API_KEY")
    suspend fun getPopular(@Query("page") page: Int):MovieModel

    @GET("/3/movie/top_rated?api_key=$API_KEY")
    suspend fun getTopRated(@Query("page") page: Int):MovieModel

    @GET("/3/movie/upcoming?api_key=$API_KEY")
    suspend fun getUpcoming(@Query("page") page: Int):MovieModel



}