package com.thenotesgiver.tmdbapimvvm.ui.ui.main

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.thenotesgiver.tmdbapimvvm.MovieAdapter
import com.thenotesgiver.tmdbapimvvm.R
import com.thenotesgiver.tmdbapimvvm.data.MovieViewModel
import com.thenotesgiver.tmdbapimvvm.data.model.MovieResult
import com.thenotesgiver.tmdbapimvvm.databinding.MovieActBinding
import com.thenotesgiver.tmdbapimvvm.ui.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class Upcoming :Fragment(R.layout.movie_act) {
    lateinit var viewModel: MovieViewModel
    private lateinit var binding: MovieActBinding
    lateinit var list: ArrayList<MovieResult>
    lateinit var movieAdapter: MovieAdapter

    private var pageNo =1
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


            viewModel = (activity as MainActivity).viewModel

        binding  = MovieActBinding.bind(view)
        CoroutineScope(Dispatchers.Main).launch{
            viewModel.getUpcoming(pageNo).collect { res->

                list = res.results as ArrayList<MovieResult>
                 movieAdapter = MovieAdapter(list)
                delay(200)
                viewModel.increasePage()


                binding.rec.apply {
                    layoutManager = GridLayoutManager(activity,2)
                    adapter =movieAdapter
                }
            }

            binding.rec.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                        Log.d("-----", "end")
                        binding.progress.visibility = View.VISIBLE
                        getData(++pageNo)
                    }
                }
            })





        }
    }
    private fun  getData(page :Int){
        CoroutineScope(Dispatchers.IO).launch {
            viewModel.getUpcoming(page).collect {
                list += it.results
                movieAdapter.notifyDataSetChanged()
                binding.progress.visibility = View.GONE

                Log.e("TAG", "onViewCreated: "+ it.results.toString())
            }
        }

    }

}