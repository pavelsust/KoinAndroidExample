package com.rockstreamer.koinandroidexample.data.api

import com.rockstreamer.koinandroidexample.data.model.User
import retrofit2.Response

interface ApiHelper {
    suspend fun getUsers(): Response<List<User>>
}