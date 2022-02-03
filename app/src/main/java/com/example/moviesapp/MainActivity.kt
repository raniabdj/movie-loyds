package com.example.moviesapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.moviesapp.databinding.ActivityMainBinding
import com.example.moviesapp.model.Titles
import com.example.moviesapp.repositories.SearchRepository
import com.example.moviesapp.services.SearchService
import com.example.moviesapp.ui.main.SearchListAdapter
import com.example.moviesapp.utils.SelectedTitleListener
import com.example.moviesapp.utils.Status
import com.example.moviesapp.viewModels.SearchViewModel
import com.example.moviesapp.viewModels.SearchViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar


class MainActivity : AppCompatActivity(), SelectedTitleListener {

    private lateinit var binding: ActivityMainBinding
    private val service = SearchService.getInstance()
    private val repository = SearchRepository(service)
    private val viewModel: SearchViewModel by lazy {
        ViewModelProvider(
            this,
            SearchViewModelFactory(repository)
        )[SearchViewModel::class.java]
    }
    private val titlesAdapter = SearchListAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.searchResult.adapter = titlesAdapter
        binding.searchResult.layoutManager =
            GridLayoutManager(
                this,
                2
            )

        configureObservers()


        binding.lunchSearch.setOnClickListener {
            Log.v(
                "EditText",
                binding.searchBar.text.toString()
            )
            viewModel.getSearchData("SearchSeries",binding.searchBar.text.toString())
            Snackbar.make(
                binding.mainLayout,
                "This is main activity ${binding.searchBar.text}",
                Snackbar.LENGTH_LONG
            )
                .setAction("CLOSE") { }
                .setActionTextColor(resources.getColor(android.R.color.holo_red_light))
                .show()
        }


        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    // Respond to navigation item 1 click
                    true
                }
                R.id.explore -> {
                    // Respond to navigation item 2 click
                    true
                }
                R.id.confirmation -> {
                    // Respond to navigation item 2 click
                    true
                }
                R.id.person -> {
                    // Respond to navigation item 2 click
                    true
                }
                else -> false
            }
        }
    }

    private fun configureObservers() {


        viewModel.searchLiveData.observe(this) {
            println(it)
            titlesAdapter.setTitlesList(it.results)
        }
    }

    override fun onSelectedTitle(selected: Titles) {
        TODO("Not yet implemented")
    }

}