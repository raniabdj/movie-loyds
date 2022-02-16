package com.example.moviesapp.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviesapp.repositories.TitlesRepository


class RankingResultViewModelFactory(private val repository: TitlesRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RankingResultViewModel(repository) as T
    }
}