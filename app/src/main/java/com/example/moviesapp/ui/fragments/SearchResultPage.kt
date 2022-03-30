package com.example.moviesapp.ui.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapp.R
import com.example.moviesapp.model.Titles
import com.example.moviesapp.repositories.SearchRepository
import com.example.moviesapp.services.SearchMovieClient
import com.example.moviesapp.services.SearchService
import com.example.moviesapp.ui.main.SearchListAdapter
import com.example.moviesapp.utils.SelectedTitleListener
import com.example.moviesapp.viewModels.SearchViewModel

class SearchResultPage : Fragment(), SelectedTitleListener {

    companion object {
        fun newInstance() = SearchResultPage()
    }

    private val service:SearchService = SearchMovieClient.getClient()
    private val repository = SearchRepository(service)
    private lateinit var viewModel: SearchViewModel
    private val titlesAdapter = SearchListAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.search_result_page_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movie = arguments?.getString("search")
        val mode = arguments?.getBoolean("mode")

        val recyclerview = view.findViewById<RecyclerView>(R.id.searchResultsList)
        if (recyclerview != null) {
            recyclerview.adapter=titlesAdapter
            recyclerview.layoutManager = LinearLayoutManager(context)
            if (mode == false) {
                movie?.let { its ->
                    viewModel=getViewModel("SearchMovie", its)
                    viewModel.searchData.observe(viewLifecycleOwner) {
                        titlesAdapter.setTitlesList(it.results)
                    }
                }

            } else {

                movie?.let {
                    viewModel=getViewModel("SearchSeries", it)
                    viewModel.searchData.observe(viewLifecycleOwner) {
                        titlesAdapter.setTitlesList(it.results)
                    }
                }

            }
        }
    }

    fun bindUI(){

    }

    override fun onSelectedTitle(selected: Titles) {
        TODO("Not yet implemented")
    }

    private fun getViewModel(type:String,title:String): SearchViewModel{

        return ViewModelProviders.of( this,object: ViewModelProvider.Factory{
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return SearchViewModel(repository,type,title) as T
            }
        })[SearchViewModel::class.java]


    }

}