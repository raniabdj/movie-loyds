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
import com.example.moviesapp.databinding.ResultsListItemBinding
import com.example.moviesapp.databinding.TitleCardViewBinding
import com.example.moviesapp.model.Titles
import com.example.moviesapp.ui.fragments.TitleDetailFragment
import com.example.moviesapp.utils.SelectedTitleListener


class SearchListAdapter(private val listener: SelectedTitleListener): RecyclerView.Adapter<TitlesViewHolder>(){

    private val titleList = mutableListOf<Titles>()

    fun setTitlesList(list: List<Titles>) {
        println("new list ------------")
        println(list)
        titleList.clear()
        titleList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TitlesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ResultsListItemBinding.inflate(inflater, parent, false)
        return TitlesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TitlesViewHolder, position: Int) {
        val currentTitles = titleList[position]

        println("---- view holder -----")
        println(currentTitles)
        holder.binding.titleName.text = currentTitles.title

        Glide.with(holder.binding.thumbPoster)
            .load(currentTitles.image)
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(holder.binding.thumbPoster)
        holder.itemView.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                //viewModel.getWeather(binding.search.text.toString())

                val bundle = Bundle()
                bundle.putString("id", currentTitles.id)
                bundle.putString("title", currentTitles.title)
                bundle.putString("image", currentTitles.image)

                val fragobj = TitleDetailFragment()
                fragobj.setArguments(bundle)
                view?.findNavController()?.navigate(R.id.titleDetailFragment, bundle)
            }

        })

      //  holder.itemView.setOnClickListener {
        //    Toast.makeText(holder.itemView.context,currentTitles.volumeInfo.subtitle, Toast.LENGTH_LONG).show()
       // }
    }

    override fun getItemCount() = titleList.size

    fun getItem(position: Int): Titles {
        return titleList[position];
    }
}

class TitlesViewHolder(val binding: ResultsListItemBinding): RecyclerView.ViewHolder(binding.root)