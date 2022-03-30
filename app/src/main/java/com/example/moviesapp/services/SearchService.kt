package com.example.moviesapp.services

import com.example.moviesapp.model.SearchData
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface SearchService {

    @GET("{type}/k_1glq0o5b/{title}")
     fun getSearchResult(
        @Path("type") type: String,
        @Path("title") title: String
    ): Single<SearchData>


}