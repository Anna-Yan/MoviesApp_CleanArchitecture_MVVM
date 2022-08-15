package com.annazaqaryan.moviesbyaz.presentation.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.annazaqaryan.moviesbyaz.domain.model.MovieDetailsResponse
import com.bumptech.glide.Glide
import com.annazaqaryan.moviesbyaz.data.source.remote.ApiService.Companion.POSTER_BASE_URL
import com.annazaqaryan.moviesbyaz.databinding.FragmentMovieDetailsBinding
import com.annazaqaryan.moviesbyaz.domain.repository.SingleResult
import com.annazaqaryan.moviesbyaz.util.toast
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.NumberFormat
import java.util.*

class MovieDetailsFragment : Fragment() {

    private lateinit var binding: FragmentMovieDetailsBinding
    private val detailViewModel: MovieDetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val movieId: Int? = arguments?.let { MovieDetailsFragmentArgs.fromBundle(it).movieId }
        detailViewModel.setMovieId(movieId?:0)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getMovieDetails()
    }

    private fun getMovieDetails() {
        detailViewModel.getMovieDetails()

        detailViewModel.movieDetails.observe(viewLifecycleOwner) {
            binding.progressBar.isVisible = false
            if (it is SingleResult.Success) {
                bindUI(it.data)
            } else {
                requireContext().toast("Failed to get Details")
            }
        }
        binding.progressBar.isVisible = true
    }

    private fun bindUI(it: MovieDetailsResponse){

        with(binding){
            this.movieTitle.text = it.title
            this.movieStatus.text = it.status
            this.movieTagline.text = it.tagline
            this.movieReleaseDate.text = it.release_date
            this.movieRating.text = it.vote_average.toString()
            this.movieRuntime.text = "${it.runtime} +  minutes"
            this.movieOverview.text = it.overview
        }
        val formatCurrency = NumberFormat.getCurrencyInstance(Locale.US)
        binding.movieBudget.text = formatCurrency.format(it.budget)

        val moviePosterURL = POSTER_BASE_URL + it.poster_path
        Glide.with(this)
            .load(moviePosterURL)
            .into(binding.ivMoviePoster);
    }

}