package com.example.moviesapp.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviesapp.model.RankedTitle
import com.example.moviesapp.model.SearchData
import com.example.moviesapp.repositories.SearchRepository
import com.example.moviesapp.repositories.TitlesRepository
import com.example.moviesapp.utils.Status

class TitleDetailPageViewModelFactory(private val repository: TitlesRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TitleDetailPageViewModel(repository) as T
    }
}