package com.example.moviesapp.utils

import android.icu.text.CaseMap
import com.example.moviesapp.model.Titles


interface SelectedTitleListener {
    fun onSelectedTitle(selected: Titles)
}