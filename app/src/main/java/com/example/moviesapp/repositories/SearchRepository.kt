package com.example.moviesapp.repositories

import androidx.lifecycle.LiveData
import com.example.moviesapp.model.SearchData
import com.example.moviesapp.services.SearchService
import com.example.moviesapp.utils.NetworkState
import io.reactivex.disposables.CompositeDisposable


class SearchRepository(private val apiservice: SearchService) {
    lateinit var searchDataNetworkDataSource: SearchDataSource
    fun fetchDataResulte(compositDisposable: CompositeDisposable, type:String, title: String): LiveData<SearchData> {
        searchDataNetworkDataSource= SearchDataSource(apiservice,compositDisposable)
        searchDataNetworkDataSource.fetchSearchResult(type, title)
        return searchDataNetworkDataSource.downloadedSearchResponse
    }

    fun getSearchDataNetworkState():LiveData<NetworkState>{
        return searchDataNetworkDataSource.networkState
    }
}