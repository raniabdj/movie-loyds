package com.example.moviesapp.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.model.RankedTitle
import com.example.moviesapp.model.SearchData
import com.example.moviesapp.model.TitleDetailes
import com.example.moviesapp.repositories.TitlesRepository
import com.example.moviesapp.utils.Status
import kotlinx.coroutines.launch

class TitleDetailPageViewModel(private val repository: TitlesRepository) : ViewModel() {

    private val _titleDetailLiveData = MutableLiveData<TitleDetailes>()
    val titleDetailLiveData: LiveData<TitleDetailes>
        get() = _titleDetailLiveData

    fun getTitleDetailData(videoId:String) {
        //_statusLiveData.postValue(Status.LOADING)
        viewModelScope.launch {
            val response = repository.getDetails(videoId )
            println(" detail ----------------------------------------------------------")
          //  if (response.isSuccessful) {
              //  _titleDetailLiveData.postValue(response.body())
                //_statusLiveData.postValue(Status.SUCCESS)
            //} else {
                // _statusLiveData.postValue(Status.ERROR)
           // }
        }
    }


}