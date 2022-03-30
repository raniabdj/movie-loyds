package com.example.moviesapp.services

import com.example.moviesapp.model.RankResponse
import com.example.moviesapp.model.RankedTitle
import com.example.moviesapp.model.SearchData
import com.example.moviesapp.model.TitleDetailes
import io.reactivex.Single
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface TitlesService {

    @GET("Top250Movies/k_1glq0o5b")
    suspend fun getTop250MoviesResult(): Response<RankResponse>

    @GET("Top250TVs/k_1glq0o5b")
    suspend fun getTop250TVsResult(): Response<RankResponse>


    @GET("YouTubeTrailer/k_1glq0o5b/{id}")
    suspend fun getTitleDetails(
        @Path("id") id: String,
    ): Single<TitleDetailes>



}