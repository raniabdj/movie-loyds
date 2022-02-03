package com.example.moviesapp.viewModels

import android.text.Editable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.model.SearchData
import com.example.moviesapp.repositories.SearchRepository
import com.example.moviesapp.utils.Status
import kotlinx.coroutines.launch

class SearchViewModel(private val repository: SearchRepository) : ViewModel() {

    private val _searchLiveData = MutableLiveData<SearchData>()
    val searchLiveData: LiveData<SearchData>
        get() = _searchLiveData

    private val _statusLiveData = MutableLiveData<Status>()
    val statusLiveData: LiveData<Status>
        get() = _statusLiveData

    fun getSearchData(type: String, title: String) {
        _statusLiveData.postValue(Status.LOADING)
        viewModelScope.launch {
            val response = repository.getResults(type,title)
            println("----------------------------------------------------------")
            println(response.body())
            if (response.isSuccessful) {
                _searchLiveData.postValue(response.body())
                _statusLiveData.postValue(Status.SUCCESS)
            } else {
                _statusLiveData.postValue(Status.ERROR)
            }
        }
    }

}