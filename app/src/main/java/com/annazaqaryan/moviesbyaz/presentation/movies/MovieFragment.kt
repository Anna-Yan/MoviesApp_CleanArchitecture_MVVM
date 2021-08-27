package com.annazaqaryan.moviesbyaz.presentation.movies

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.recyclerview.widget.GridLayoutManager
import com.annazaqaryan.moviesbyaz.MovieService.Companion.KEY_ACTION
import com.annazaqaryan.moviesbyaz.R
import com.annazaqaryan.moviesbyaz.databinding.FragmentMovieListBinding
import com.annazaqaryan.moviesbyaz.domain.repository.SingleResult
import com.annazaqaryan.moviesbyaz.util.toast
import org.koin.androidx.viewmodel.ext.android.viewModel


class MovieFragment : Fragment() {

    private lateinit var movieAdapter: MovieListAdapter
    private val movieViewModel: MovieViewModel by viewModel()
    private var binding: FragmentMovieListBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        movieAdapter = MovieListAdapter(requireContext())
    }

    private val receiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val headerText = intent.getStringExtra("Extra")
            binding!!.header.text = headerText
        }
    }

    override fun onStart() {
        super.onStart()
        LocalBroadcastManager.getInstance(requireActivity()).registerReceiver(
            receiver, IntentFilter(KEY_ACTION))
    }

    override fun onStop() {
        super.onStop()
        LocalBroadcastManager.getInstance(requireActivity()).unregisterReceiver(receiver)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movie_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMovieListBinding.bind(view)
        initAdapter()
        getPopularMovies()
    }

    private fun initAdapter() {
        val gridLayoutManager = GridLayoutManager(requireContext(), 2)
        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                val viewType = movieAdapter.getItemViewType(position)
                if (viewType == movieAdapter.VIEW_TYPE_MOVIE)
                    return  1
                else
                    return 2
            }
        }
        binding!!.recyclerPopularMovies.layoutManager = gridLayoutManager
        binding!!.recyclerPopularMovies.setHasFixedSize(true)
        binding!!.recyclerPopularMovies.adapter = movieAdapter
    }


    private fun getPopularMovies() {
        movieViewModel.getMoviesList()

        movieViewModel.moviesDataList.observe(viewLifecycleOwner, {
            binding!!.progressBar.isVisible = false
            if (it is SingleResult.Success) {
                movieAdapter.submitList(it.data)
            } else {
                requireContext().toast("Failed to get movies")
            }
        })
        binding!!.progressBar.isVisible = true

    }

}