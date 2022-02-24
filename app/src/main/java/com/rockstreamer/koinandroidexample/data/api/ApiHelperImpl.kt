package com.rockstreamer.koinandroidexample.data.api

import com.rockstreamer.koinandroidexample.data.model.MovieResponse
import retrofit2.Response

class ApiHelperImpl (private val apiService: ApiService): ApiHelper {

    override suspend fun getMovieList(token: String, page: Int): Response<MovieResponse> {
        return apiService.getMovieList(token , page)
    }
}