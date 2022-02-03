package com.example.moviesapp.services

import com.example.moviesapp.model.SearchData
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface SearchService {

    @GET("{type}/k_1glq0o5b/{title}")
    suspend fun getSearchResult(
        @Path("type") type: String,
        @Path("title") title: String
    ): Response<SearchData>

    companion object {
        var retrofitService: SearchService? = null
        fun getInstance(): SearchService {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://imdb-api.com/en/API/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(SearchService::class.java)
            }
            return retrofitService!!
        }

    }
}