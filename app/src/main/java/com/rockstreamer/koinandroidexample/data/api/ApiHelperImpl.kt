package com.rockstreamer.koinandroidexample.data.api

import com.rockstreamer.koinandroidexample.data.model.User
import retrofit2.Response

class ApiHelperImpl (val apiService: ApiService): ApiHelper {
    override suspend fun getUsers(): Response<List<User>> {
        return apiService.getUsers()
    }
}