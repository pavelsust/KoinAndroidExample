package com.rockstreamer.koinandroidexample.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rockstreamer.koinandroidexample.data.model.MovieItem
import com.rockstreamer.koinandroidexample.data.model.MovieResponse
import com.rockstreamer.koinandroidexample.data.repository.MainRepository
import com.rockstreamer.koinandroidexample.utils.NetworkHelper
import com.rockstreamer.koinandroidexample.utils.Resource
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainViewModel(
    private val mainRepository: MainRepository,
    private val networkHelper: NetworkHelper
) : ViewModel(){
    private val _movieListResponse = MutableLiveData<Resource<MovieResponse>>()
    private val _mutableMovieList : MutableList<MovieItem> = ArrayList()
    private val movieList = MutableLiveData<MutableList<MovieItem>>()

    val movieListResponse get() = _movieListResponse
    val mutableMovieList get() =  movieList

    init {
        fetchMovie("e4c41ae3e8578a454aa7575f144a0f14" , 1)
    }

    fun fetchMovie(token :String, page:Int){
         GlobalScope.launch {
             _movieListResponse.postValue(Resource.loading(null))
             if (networkHelper.isNetworkConnected()){
                 mainRepository.getMovieList(token , page).let {
                     if (it.isSuccessful){
                         _mutableMovieList.addAll(it.body()!!.results)
                         movieList.postValue(_mutableMovieList)
                         _movieListResponse.postValue(Resource.success(it.body()))
                     } else _movieListResponse.postValue(Resource.error(it.errorBody().toString(), null))
                 }
             } else _movieListResponse.postValue(Resource.error("No Internet connection", null))
         }
    }
}