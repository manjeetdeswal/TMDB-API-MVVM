package com.thenotesgiver.tmdbapimvvm.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.thenotesgiver.tmdbapimvvm.MovieAdapter
import com.thenotesgiver.tmdbapimvvm.data.MovieViewModel
import com.thenotesgiver.tmdbapimvvm.data.model.MovieResult
import com.thenotesgiver.tmdbapimvvm.data.paging.SpinnerAdapter
import com.thenotesgiver.tmdbapimvvm.databinding.ActivityMovieManBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.collections.ArrayList

@AndroidEntryPoint
class MovieManAct : AppCompatActivity() {

    private lateinit var binding:ActivityMovieManBinding

    private val paths = arrayOf(
        "Upcoming", "TopRated", "Popular", "NowPlaying",

    )

    val  viewModel by viewModels<MovieViewModel> ()

    lateinit var list0: ArrayList<MovieResult>
    lateinit var list1: ArrayList<MovieResult>
    lateinit var list2: ArrayList<MovieResult>
    lateinit var list3: ArrayList<MovieResult>
    lateinit var movieAdapter: MovieAdapter

    private var pageNo =1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieManBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val categoriesAdapter =
            SpinnerAdapter(this, paths)

        binding.spinner1.setAdapter(categoriesAdapter)






   /*     val sharedPref1 = getSharedPreferences("FileName", MODE_PRIVATE)
        val spinnerValue = sharedPref1.getInt("userChoiceSpinner", 1)
        binding.spinner1.setSelection(spinnerValue)
*/

        binding.spinner1.onItemSelectedListener =object : AdapterView.OnItemSelectedListener{

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val userChoice =  binding.spinner1.selectedItemPosition
               /* val sharedPref = getSharedPreferences("FileName", 0)
                val prefEditor = sharedPref.edit()
                prefEditor.putInt("userChoiceSpinner", userChoice)
                prefEditor.apply()*/

                val selected = paths[userChoice]
                Log.e("TAG", "onItemSelected: "+userChoice )

                  getTag(userChoice)

                binding.rec.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                        super.onScrollStateChanged(recyclerView, newState)
                        if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                            Log.d("-----", "end")
                            binding.progress.visibility = View.VISIBLE
                            getData(++pageNo,userChoice)

                        }
                    }
                })



            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }












    }


    private fun getTag(tag:Int) {
        list0 = ArrayList()
        list1 = ArrayList()
        list2 = ArrayList()
        list3 = ArrayList()

        if (tag ==0){


            CoroutineScope(Dispatchers.Main).launch {

                viewModel.getUpcoming(pageNo).collect{
                    list0.clear()
                    list0 =it.results
                    movieAdapter = MovieAdapter(list0)

                    binding.rec.apply {
                        layoutManager = GridLayoutManager(context,2)
                        adapter =movieAdapter
                    }

                }
            }
        }

        else if (tag ==1){
            list1.add(MovieResult(true,"", emptyList(),1,"","","",5.0,"","",
                "",false,13.5,100))
            list1.clear()
            CoroutineScope(Dispatchers.Main).launch {

                viewModel.getTopRated(pageNo).collect{
                    list1.clear()
                    list1 =it.results
                    movieAdapter = MovieAdapter(list1)

                    binding.rec.apply {
                        layoutManager = GridLayoutManager(context,2)
                        adapter =movieAdapter
                    }

                }
            }
        }


        else   if (tag ==2){
            list2.add(MovieResult(true,"", emptyList(),1,"","","",5.0,"","",
                "",false,13.5,100))
            list2.clear()
            CoroutineScope(Dispatchers.Main).launch {

                viewModel.getPopular(pageNo).collect{
                    list2.clear()
                    list2 =it.results
                    movieAdapter = MovieAdapter(list2)

                    binding.rec.apply {
                        layoutManager = GridLayoutManager(context,2)
                        adapter =movieAdapter
                    }

                }
            }
        }


        else if (tag ==3){
            CoroutineScope(Dispatchers.Main).launch {

                viewModel.getNowPlaying(pageNo).collect{
                    list3.clear()
                    list3 =it.results
                    movieAdapter = MovieAdapter(list3)

                    binding.rec.apply {
                        layoutManager = GridLayoutManager(context,2)
                        adapter =movieAdapter
                    }

                }
            }
             }

    }





    private fun  getData(page :Int,item :Int){
        CoroutineScope(Dispatchers.IO).launch {

            if (item == 0){
                viewModel.getUpcoming(page).collect {
                    list0 += it.results
                    movieAdapter.notifyDataSetChanged()
                    binding.progress.visibility = View.GONE

                    Log.e("TAG", "onViewCreated: "+ it.results.toString())
                }
            }

            if (item == 1){
                viewModel.getTopRated(page).collect {
                    list1 += it.results
                    movieAdapter.notifyDataSetChanged()
                    binding.progress.visibility = View.GONE

                    Log.e("TAG", "onViewCreated: "+ it.results.toString())
                }
            }

            if (item == 2){
                viewModel.getPopular(page).collect {
                    list2 += it.results
                    movieAdapter.notifyDataSetChanged()
                    binding.progress.visibility = View.GONE

                    Log.e("TAG", "onViewCreated: "+ it.results.toString())
                }
            }

            if (item == 3){
                viewModel.getNowPlaying(page).collect {
                    list3 += it.results
                    movieAdapter.notifyDataSetChanged()
                    binding.progress.visibility = View.GONE

                    Log.e("TAG", "onViewCreated: "+ it.results.toString())
                }
            }




        }

    }
}