package com.example.moviesapp.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.moviesapp.R
import com.example.moviesapp.viewModels.TitleDetailPageViewModel

class TitleDetailPage : Fragment() {

    companion object {
        fun newInstance() = TitleDetailPage()
    }

    private lateinit var viewModel: TitleDetailPageViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.title_detail_page_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TitleDetailPageViewModel::class.java)
        // TODO: Use the ViewModel
    }

}