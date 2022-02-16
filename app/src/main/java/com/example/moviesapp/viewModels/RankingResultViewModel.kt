package com.example.moviesapp.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.model.RankResponse
import com.example.moviesapp.model.RankedTitle
import com.example.moviesapp.repositories.TitlesRepository
import com.example.moviesapp.utils.Status
import kotlinx.coroutines.launch


class RankingResultViewModel(private val repository: TitlesRepository) : ViewModel() {

    private val _moviesLiveData = MutableLiveData<RankResponse>()
    val moviesLiveData: LiveData<RankResponse>
        get() = _moviesLiveData

    private val _seriesLiveData = MutableLiveData<RankResponse>()
    val seriesLiveData: LiveData<RankResponse>
        get() = _seriesLiveData

    fun getMoviesData() {
       // _moviesLiveData.postValue(Status.LOADING)
        viewModelScope.launch {
            val response = repository.getTop250Movies()

            if (response.isSuccessful) {
                _moviesLiveData.postValue(response.body())
               //_statusLiveData.postValue(Status.SUCCESS)
            } else {
               // _statusLiveData.postValue(Status.ERROR)
            }
        }
    }

    fun getSeriesData() {
        //_statusLiveData.postValue(Status.LOADING)
        viewModelScope.launch {
            val response = repository.getTop250Serie()

            if (response.isSuccessful) {
                _seriesLiveData.postValue(response.body())
                //_statusLiveData.postValue(Status.SUCCESS)
            } else {
               // _statusLiveData.postValue(Status.ERROR)
            }
        }
    }


}