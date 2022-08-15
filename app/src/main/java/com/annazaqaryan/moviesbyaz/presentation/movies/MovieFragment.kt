package com.annazaqaryan.moviesbyaz.presentation.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.annazaqaryan.moviesbyaz.databinding.FragmentMovieListBinding
import com.annazaqaryan.moviesbyaz.domain.repository.SingleResult
import com.annazaqaryan.moviesbyaz.util.toast
import org.koin.androidx.viewmodel.ext.android.viewModel


class MovieFragment : Fragment() {

    private lateinit var movieAdapter: MovieListAdapter
    private val movieViewModel: MovieViewModel by viewModel()
    private lateinit var binding: FragmentMovieListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        movieAdapter = MovieListAdapter(requireContext())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentMovieListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.header.text = "MOVIES FROM themoviedb.org"
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
        binding.recyclerPopularMovies.layoutManager = gridLayoutManager
        binding.recyclerPopularMovies.setHasFixedSize(true)
        binding.recyclerPopularMovies.adapter = movieAdapter
    }


    private fun getPopularMovies() {
        movieViewModel.getMoviesList()

        movieViewModel.moviesDataList.observe(viewLifecycleOwner) {
            binding.progressBar.isVisible = false
            if (it is SingleResult.Success) {
                movieAdapter.submitList(it.data)
            } else {
                requireContext().toast("Failed to get movies")
            }
        }
        binding.progressBar.isVisible = true

    }

}