package com.example.moviesapp.repositories

import com.example.moviesapp.services.SearchService

class SearchRepository(private val service: SearchService) {
    suspend fun getResults(type: String, title: String) =
        service.getSearchResult(type, title)

}