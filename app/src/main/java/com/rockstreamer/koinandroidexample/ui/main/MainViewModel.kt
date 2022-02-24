package com.rockstreamer.koinandroidexample.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rockstreamer.koinandroidexample.data.model.MovieResponse
import com.rockstreamer.koinandroidexample.data.repository.MainRepository
import com.rockstreamer.koinandroidexample.utils.NetworkHelper
import com.rockstreamer.koinandroidexample.utils.Resource
import kotlinx.coroutines.launch

class MainViewModel(
    private val mainRepository: MainRepository,
    private val networkHelper: NetworkHelper
) : ViewModel(){
    private val _movieListResponse = MutableLiveData<Resource<MovieResponse>>()
    val movieListResponse get() = _movieListResponse

     fun fetchMovie(token :String , page:Int){
        viewModelScope.launch {
            _movieListResponse.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()){
                mainRepository.getMovieList(token , 1).let {
                    if (it.isSuccessful){
                        _movieListResponse.postValue(Resource.success(it.body()))
                    } else _movieListResponse.postValue(Resource.error(it.errorBody().toString(), null))
                }
            } else _movieListResponse.postValue(Resource.error("No Internet connection", null))
        }
    }
}