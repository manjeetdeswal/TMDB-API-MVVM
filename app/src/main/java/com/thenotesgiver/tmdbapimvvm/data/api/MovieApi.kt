package com.thenotesgiver.tmdbapimvvm.data.api

import com.thenotesgiver.tmdbapimvvm.data.model.MovieModel
import com.thenotesgiver.tmdbapimvvm.helper.Constant
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {




    @GET("/3/movie/now_playing?api_key=80b08ade763b581df00679a26ce51f3a")
    suspend fun getNowPlaying(@Query("page") page: Int):MovieModel

    @GET("/3/movie/popular?api_key=80b08ade763b581df00679a26ce51f3a")
    suspend fun getPopular(@Query("page") page: Int):MovieModel

    @GET("/3/movie/top_rated?api_key=80b08ade763b581df00679a26ce51f3a")
    suspend fun getTopRated(@Query("page") page: Int):MovieModel

    @GET("/3/movie/upcoming?api_key=80b08ade763b581df00679a26ce51f3a")
    suspend fun getUpcoming(@Query("page") page: Int):MovieModel



}