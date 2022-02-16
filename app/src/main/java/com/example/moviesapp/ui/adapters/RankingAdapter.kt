package com.example.moviesapp.ui.main

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.example.moviesapp.R
import com.example.moviesapp.databinding.TitleCardViewBinding
import com.example.moviesapp.model.RankedTitle
import com.example.moviesapp.ui.fragments.TitleDetailFragment
import com.example.moviesapp.utils.SelectedRankedTitleListener


class RankingAdapter(private val listener: SelectedRankedTitleListener) :
    RecyclerView.Adapter<ResultsViewHolder>() {

    private val titleList = mutableListOf<RankedTitle>()

    fun setTitlesList(list: List<RankedTitle>) {
        titleList.clear()
        titleList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = TitleCardViewBinding.inflate(inflater, parent, false)
        return ResultsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ResultsViewHolder, position: Int) {


        val currentTitles = titleList[position]


        holder.binding.movieTitle.text = currentTitles.title

        Glide.with(holder.binding.moviePoster)
            .load(currentTitles.image)
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(
                object : SimpleTarget<Drawable?>() {
                    override fun onResourceReady(
                        resource: Drawable,
                        transition: Transition<in Drawable?>?
                    ) {
                        holder.binding.moviePoster.background = resource
                    }
                }
            )


          holder.itemView.setOnClickListener(object : View.OnClickListener {
              override fun onClick(view: View?) {
                  //viewModel.getWeather(binding.search.text.toString())
                  Toast.makeText(holder.itemView.context,currentTitles.fullTitle, Toast.LENGTH_LONG).show()
                  val bundle = Bundle()
                  bundle.putString("id", currentTitles.id)
                  bundle.putString("title", currentTitles.fullTitle)
                  bundle.putString("ratingImdb", currentTitles.imDbRating)
                  bundle.putString("countImdb", currentTitles.imDbRatingCount)
                  bundle.putString("image", currentTitles.image)
                  bundle.putString("rank", currentTitles.rank)
                  bundle.putString("year", currentTitles.year)
                  bundle.putString("crew", currentTitles.crew)

                  val fragobj = TitleDetailFragment()
                  fragobj.setArguments(bundle)
                  view?.findNavController()?.navigate(R.id.titleDetailFragment, bundle)
              }

          })

    }

    override fun getItemCount() = titleList.size

    fun getItem(position: Int): RankedTitle {
        return titleList[position];
    }
}

class ResultsViewHolder(val binding: TitleCardViewBinding) : RecyclerView.ViewHolder(binding.root)