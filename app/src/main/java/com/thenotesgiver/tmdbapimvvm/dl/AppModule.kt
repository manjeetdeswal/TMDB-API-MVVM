package com.thenotesgiver.tmdbapimvvm.dl

import com.thenotesgiver.tmdbapimvvm.helper.Constant
import com.thenotesgiver.tmdbapimvvm.data.api.MovieApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Singleton
    @Provides
    fun  providesApi():MovieApi{

        return Retrofit.Builder().baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(MovieApi::class.java)
    }
}