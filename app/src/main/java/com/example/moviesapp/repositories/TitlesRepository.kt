package com.example.moviesapp.repositories

import com.example.moviesapp.services.SearchService
import com.example.moviesapp.services.TitlesService


class TitlesRepository(private val service: TitlesService) {
    suspend fun getTop250Movies() =
        service.getTop250MoviesResult()

    suspend fun getTop250Serie() =
        service.getTop250TVsResult()

    suspend fun getDetails(id: String) =
        service.getTitleDetails(id)

}