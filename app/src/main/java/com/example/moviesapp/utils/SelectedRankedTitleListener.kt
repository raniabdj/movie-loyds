package com.example.moviesapp.utils

import android.icu.text.CaseMap
import com.example.moviesapp.model.RankedTitle
import com.example.moviesapp.model.Titles


interface SelectedRankedTitleListener {
    fun onSelectedTitle(selected: RankedTitle)
}