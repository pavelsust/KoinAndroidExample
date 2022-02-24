package com.rockstreamer.koinandroidexample.data.api

import com.rockstreamer.koinandroidexample.data.model.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("movie/top_rated")
    suspend fun getMovieList(
        @Query("api_key") apiKey: String ,
        @Query("page") page: Int): Response<MovieResponse>

}