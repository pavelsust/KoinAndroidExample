package com.rockstreamer.koinandroidexample.data.api

import com.rockstreamer.koinandroidexample.data.model.MovieResponse
import retrofit2.Response

interface ApiHelper {
    suspend fun getMovieList(token :String , page: Int): Response<MovieResponse>
}