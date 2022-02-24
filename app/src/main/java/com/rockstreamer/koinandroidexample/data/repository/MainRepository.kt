package com.rockstreamer.koinandroidexample.data.repository

import com.rockstreamer.koinandroidexample.data.api.ApiHelper

class MainRepository (private val apiHelper: ApiHelper) {
    suspend fun getMovieList(token :String , page:Int) = apiHelper.getMovieList(token , page)
}