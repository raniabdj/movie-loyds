package com.example.moviesapp.ui.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapp.R
import com.example.moviesapp.model.Titles
import com.example.moviesapp.repositories.SearchRepository
import com.example.moviesapp.services.SearchService
import com.example.moviesapp.ui.main.SearchListAdapter
import com.example.moviesapp.utils.SelectedTitleListener
import com.example.moviesapp.viewModels.SearchViewModel
import com.example.moviesapp.viewModels.SearchViewModelFactory

class SearchResultPage : Fragment(), SelectedTitleListener {

    companion object {
        fun newInstance() = SearchResultPage()
    }

    private val service = SearchService.getInstance()
    private val repository = SearchRepository(service)
    private val viewModel: SearchViewModel by lazy {
        ViewModelProvider(
            this,
            SearchViewModelFactory(repository)
        )[SearchViewModel::class.java]
    }
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
                    viewModel.getSearchData("SearchMovie", its)
                    viewModel.searchLiveData.observe(viewLifecycleOwner) {
                        println(" movies ----------------------------------------------------------")
                        titlesAdapter.setTitlesList(it.results)
                    }
                }

            } else {

                movie?.let { viewModel.getSearchData("SearchSeries", it)

                    viewModel.searchLiveData.observe(viewLifecycleOwner) {
                        println("series ----------------------------------------------------------")
                        titlesAdapter.setTitlesList(it.results)
                    }
                }

            }
        }
    }


    override fun onSelectedTitle(selected: Titles) {
        TODO("Not yet implemented")
    }

}