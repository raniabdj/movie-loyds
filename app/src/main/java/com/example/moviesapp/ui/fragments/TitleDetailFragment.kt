package com.example.moviesapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.moviesapp.databinding.TitleDetailPageFragmentBinding
import com.example.moviesapp.model.RankedTitle
import com.example.moviesapp.repositories.TitlesRepository
import com.example.moviesapp.services.TitleServiceClient
import com.example.moviesapp.services.TitlesService
import com.example.moviesapp.utils.SelectedRankedTitleListener
import com.example.moviesapp.viewModels.RankingResultViewModel
import com.example.moviesapp.viewModels.RankingResultViewModelFactory
import com.example.moviesapp.viewModels.TitleDetailPageViewModel
import com.example.moviesapp.viewModels.TitleDetailPageViewModelFactory
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener





class TitleDetailFragment : Fragment(), SelectedRankedTitleListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    private val service:TitlesService = TitleServiceClient.getClient()
    private val repository = TitlesRepository(service)
    private val viewModel: TitleDetailPageViewModel by lazy {
        ViewModelProvider(
            this,
            TitleDetailPageViewModelFactory(repository)
        )[TitleDetailPageViewModel::class.java]
    }


    private lateinit var binding: TitleDetailPageFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = TitleDetailPageFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movie = arguments?.getString("id")
        //  if (city != null) {
        //      viewModel.getWeather(city)
        //  }

        val img = arguments?.getString("image")
        val title = arguments?.getString("title")
        val rating = arguments?.getString("ratingImdb")
        val textVoteCount = arguments?.getString("countImdb")
        val textDuration = arguments?.getString("image")
        val textReleaseDate = arguments?.getString("rank")
        val textOverview = arguments?.getString("year")
        if (img != null &&
            title != null &&
            rating != null &&
            textVoteCount != null &&
            textDuration != null &&
            textReleaseDate != null &&
            textOverview != null
        ) {

            binding.mvRating.visibility = View.GONE
            binding.titleMv.text=title

            if (movie != null) {
                getCurrentTitleDetails(movie)
            }


           // updateTemperatures(temp.toDouble(), feels.toDouble())
            //updateCondition(condition)
            //updatePrecipitation(it.list[0].rain)
            //updateWind(deg, speed.toDouble())
            //updateVisibility(visibility.toDouble())
        }

        //configureObservers()

        (activity as? AppCompatActivity)?.supportActionBar?.title = title



    }

    private fun getCurrentTitleDetails(id:String) {

        viewModel.getTitleDetailData(id)
        viewModel.titleDetailLiveData.observe(viewLifecycleOwner) {
            binding.youtubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    val videoId = it.videoId
                    youTubePlayer.loadVideo(videoId, 0f)
                }
            })

        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TitleDetailFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }

    override fun onSelectedTitle(selected: RankedTitle) {
        TODO("Not yet implemented")
    }
}