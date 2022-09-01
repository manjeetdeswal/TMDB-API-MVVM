package com.thenotesgiver.tmdbapimvvm.data.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.thenotesgiver.tmdbapimvvm.data.model.MovieResult
import com.thenotesgiver.tmdbapimvvm.databinding.ItemMovieBinding

class TMDBPagingAdapter : PagingDataAdapter<MovieResult, TMDBPagingAdapter.MovieViewHolder>(COMPERATOR) {


    inner class MovieViewHolder(binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {

        val name = binding.name
        val img = binding.img
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.name.text = item.title
            Glide.with(holder.itemView.context).load(item.poster_path).into(holder.img)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(view)
    }

    companion object {
       private val COMPERATOR = object  :DiffUtil.ItemCallback<MovieResult>(){
           override fun areItemsTheSame(oldItem: MovieResult, newItem: MovieResult): Boolean {
               return oldItem.id == newItem.id
           }

           override fun areContentsTheSame(oldItem: MovieResult, newItem: MovieResult): Boolean {
               return oldItem == newItem
           }

       }
    }
}