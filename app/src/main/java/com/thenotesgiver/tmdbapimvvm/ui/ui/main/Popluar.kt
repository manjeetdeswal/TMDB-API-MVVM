package com.thenotesgiver.tmdbapimvvm.ui.ui.main

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.thenotesgiver.tmdbapimvvm.MovieAdapter
import com.thenotesgiver.tmdbapimvvm.R
import com.thenotesgiver.tmdbapimvvm.data.MovieViewModel
import com.thenotesgiver.tmdbapimvvm.data.model.MovieResult
import com.thenotesgiver.tmdbapimvvm.databinding.MovieActBinding
import com.thenotesgiver.tmdbapimvvm.ui.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class Popluar :Fragment(R.layout.movie_act) {

    lateinit var viewModel: MovieViewModel
    private lateinit var binding: MovieActBinding
    lateinit var list: ArrayList<MovieResult>
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

            viewModel = (activity as MainActivity).viewModel

        binding  = MovieActBinding.bind(view)
        CoroutineScope(Dispatchers.Main).launch{
            viewModel.getPopular().collect { res->

                list = res.results as ArrayList<MovieResult>
                val movieAdapter = MovieAdapter(list)
                binding.rec.apply {
                    layoutManager = GridLayoutManager(activity,2)
                    adapter =movieAdapter
                }
            }


        }
    }

}