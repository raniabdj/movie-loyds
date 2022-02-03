package com.example.moviesapp.ui.main

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.example.moviesapp.R
import com.example.moviesapp.databinding.TitleCardViewBinding
import com.example.moviesapp.model.Titles
import com.example.moviesapp.utils.SelectedTitleListener


class SearchListAdapter(private val listener: SelectedTitleListener): RecyclerView.Adapter<TitlesViewHolder>(){

    private val titleList = mutableListOf<Titles>()

    fun setTitlesList(list: List<Titles>) {
        titleList.clear()
        titleList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TitlesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = TitleCardViewBinding.inflate(inflater, parent, false)
        return TitlesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TitlesViewHolder, position: Int) {
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



      //  holder.itemView.setOnClickListener {
        //    Toast.makeText(holder.itemView.context,currentTitles.volumeInfo.subtitle, Toast.LENGTH_LONG).show()
       // }
    }

    override fun getItemCount() = titleList.size

    fun getItem(position: Int): Titles {
        return titleList[position];
    }
}

class TitlesViewHolder(val binding: TitleCardViewBinding): RecyclerView.ViewHolder(binding.root)