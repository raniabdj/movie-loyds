package com.example.moviesapp.ui.fragments

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.moviesapp.R
import com.example.moviesapp.databinding.FragmentMainBinding
import com.example.moviesapp.model.RankedTitle
import com.example.moviesapp.model.Titles
import com.example.moviesapp.repositories.TitlesRepository
import com.example.moviesapp.services.TitleServiceClient
import com.example.moviesapp.services.TitlesService
import com.example.moviesapp.ui.main.RankingAdapter
import com.example.moviesapp.utils.SelectedRankedTitleListener
import com.example.moviesapp.utils.SelectedTitleListener
import com.example.moviesapp.viewModels.RankingResultViewModel
import com.example.moviesapp.viewModels.RankingResultViewModelFactory
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.jackandphantom.carouselrecyclerview.CarouselLayoutManager

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MainFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainFragment : Fragment(), SelectedRankedTitleListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    private val service:TitlesService =TitleServiceClient.getClient()
    private val repository = TitlesRepository(service)
    private val viewModel: RankingResultViewModel by lazy {
        ViewModelProvider(
            this,
            RankingResultViewModelFactory(repository)
        )[RankingResultViewModel::class.java]
    }


    private lateinit var binding: FragmentMainBinding
    private val titlesAdapter = RankingAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.searchResult.adapter = titlesAdapter
//
        binding.switchMod.setOnCheckedChangeListener { compoundButton, b ->
            if (b) {
                configureObservers(false)
                binding.mode.text="Series"
                binding.mainContanerFragment.setBackgroundResource(R.drawable.backgound_series)
            } else {
                configureObservers(true)
                binding.mode.text="Movies"
                binding.mainContanerFragment.setBackgroundResource(R.drawable.background_gradiant)

            }
        }

        configureObservers(true)

        binding.closeBtn.setOnClickListener {
            Firebase.auth.signOut()
            view?.findNavController()?.navigate(R.id.logIn)
        }

        binding.lunchSearch.setOnClickListener {
            Log.v(
                "EditText",
                binding.searchBar.text.toString()
            )
            val bundle = Bundle()
            bundle.putString("search", binding.searchBar.text.toString())
            bundle.putBoolean("mode", binding.switchMod.isChecked)

            view.findNavController().navigate(R.id.searchResultPage, bundle)
            // viewModel.getSearchData("SearchSeries", binding.searchBar.text.toString())
            //  Snackbar.make(
            //      binding.mainLayout,
            //      "This is main activity ${binding.searchBar.text}",
            //      Snackbar.LENGTH_LONG
            //  )
            //      .setAction("CLOSE") { }
            //      .setActionTextColor(resources.getColor(android.R.color.holo_red_light))
            //      .show()
        }

    }

    private fun configureObservers(check: Boolean) {
        if (check) {
            viewModel.getMoviesData()
            viewModel.moviesLiveData.observe(viewLifecycleOwner) {
                titlesAdapter.setTitlesList(it.items)
            }
        } else {
            viewModel.getSeriesData()
            viewModel.seriesLiveData.observe(viewLifecycleOwner) {
                titlesAdapter.setTitlesList(it.items)
            }


        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MainFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MainFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }


    override fun onSelectedTitle(selected: RankedTitle) {

    }
}