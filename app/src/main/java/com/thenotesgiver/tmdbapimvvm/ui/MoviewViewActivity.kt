package com.thenotesgiver.tmdbapimvvm.ui

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.thenotesgiver.tmdbapimvvm.data.model.MovieResult
import com.thenotesgiver.tmdbapimvvm.databinding.ActivityMainBinding

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.InputStream


class MoviewViewActivity : AppCompatActivity() {


     private lateinit var binding: ActivityMainBinding
    var movie : MovieResult?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val type = object : TypeToken<MovieResult?>() {}.getType()
         movie = Gson().fromJson(intent.getStringExtra("movie"), type)

        binding.companies.text =movie!!.original_language
         binding.title.text =  movie!!.title
        binding.overviewText.text =  movie!!.overview
        if (movie!!.adult!! ){
            binding.adult.text = "18+ : Yes"
        }else{
            binding.adult.text = "18+ : No"
        }

         binding.rating.text =  movie!!.vote_average.toString()
         binding.votes.text = movie!!.vote_count.toString()


        loadImageFromWebUrl("https://image.tmdb.org/t/p/w500" + movie!!.backdrop_path)
         Glide.with(this).load("https://image.tmdb.org/t/p/w500"+movie!!.poster_path).into(binding.poster)
         binding.year.text =  movie!!.release_date









    }

    private fun loadImageFromWebUrl(url: String?) {
        lifecycleScope.launch(Dispatchers.IO) {
            val image  = try {
                val iStream = java.net.URL(url).content as InputStream
                Drawable.createFromStream(iStream, "src name")
            } catch (e: Exception) {
                null
            }

            image?.let {
                withContext(Dispatchers.Main){
                    binding.detailsLayout.background = image
                }
            }
        }
    }

}