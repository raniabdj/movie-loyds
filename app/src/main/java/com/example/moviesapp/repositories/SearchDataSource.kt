package com.example.moviesapp.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviesapp.model.SearchData
import com.example.moviesapp.services.SearchMovieClient
import com.example.moviesapp.services.SearchService
import com.example.moviesapp.utils.NetworkState
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class SearchDataSource(private val apiService: SearchService, private val compositeDisposable: CompositeDisposable) {
    private val _networkState = MutableLiveData<NetworkState>()
    val networkState: LiveData<NetworkState>
        get() = _networkState

    private val _downloadedSearchResponse = MutableLiveData<SearchData>()
    val downloadedSearchResponse: LiveData<SearchData>
        get()= _downloadedSearchResponse

    fun fetchSearchResult(type: String, title: String){
        _networkState.postValue(NetworkState.LOADING)
        try{
            compositeDisposable.add(
                apiService.getSearchResult(type,title).subscribeOn(Schedulers.io())

                    .subscribe(
                        {
                            _downloadedSearchResponse.postValue(it)
                            _networkState.postValue(NetworkState.LOADED)

                        },
                        {
                            _networkState.postValue(NetworkState.ERROR)
                            Log.e("BookDetailsDataSource", it.message.toString())
                        }
                    )
            )

        }
        catch(e:Exception){
            Log.e("BookDetailsDataSource", e.message!!)
        }
    }

}