package com.thenotesgiver.tmdbapimvvm

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.thenotesgiver.tmdbapimvvm.data.model.MovieResult
import com.thenotesgiver.tmdbapimvvm.databinding.ItemMovieBinding

class MovieAdapter (val list: List<MovieResult>) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {



    inner class MovieViewHolder(binding: ItemMovieBinding) :RecyclerView.ViewHolder (binding.root){

        val name = binding.name
        val img =binding.img
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = ItemMovieBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = list[position]
        holder.name.text =movie.title
        Glide.with(holder.img.context).load("https://image.tmdb.org/t/p/w500" +movie.poster_path).into(holder.img)

        holder.itemView.setOnClickListener {
            val  intent =Intent(holder.itemView.context,MoviewViewActivity::class.java)
            intent.putExtra("movie", Gson().toJson(movie))
            holder.img.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}