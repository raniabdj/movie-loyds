package com.example.moviesapp.model

import android.content.ClipData

data class RankResponse(
    val errorMessage: String,
    val items: List<RankedTitle>
)

data class RankedTitle(
    val crew: String,
    val fullTitle: String,
    val id: String,
    val imDbRating: String,
    val imDbRatingCount: String,
    val image: String,
    val rank: String,
    val title: String,
    val year: String
)