package com.example.moviesapp.viewModels

import android.text.Editable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.model.SearchData
import com.example.moviesapp.repositories.SearchDataSource
import com.example.moviesapp.repositories.SearchRepository
import com.example.moviesapp.utils.NetworkState
import com.example.moviesapp.utils.Status
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.launch

class SearchViewModel(private val repository: SearchRepository,type:String,title:String) : ViewModel() {

    private val compositDisposable = CompositeDisposable()

    val searchData : LiveData<SearchData> by lazy {
        repository.fetchDataResulte(compositDisposable,type,title)
    }
    val networkState: LiveData<NetworkState> by lazy{
        repository.getSearchDataNetworkState()


    }
    override fun onCleared() {
        super.onCleared()
        compositDisposable.dispose()
    }

}

