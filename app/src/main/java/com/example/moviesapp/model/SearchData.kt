package com.example.moviesapp.model



data class SearchData(
    val errorMessage: String,
    val expression: String,
    val results: List<Titles>,
    val searchType: String
)

