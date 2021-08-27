package com.annazaqaryan.moviesbyaz.presentation.movies.viewHolder

import android.content.Context
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.annazaqaryan.moviesbyaz.data.source.remote.ApiService
import com.annazaqaryan.moviesbyaz.databinding.ItemMovieBinding
import com.annazaqaryan.moviesbyaz.domain.model.Movie
import com.annazaqaryan.moviesbyaz.presentation.movies.MovieFragmentDirections
import com.bumptech.glide.Glide

class MovieViewHolder (private val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(movie: Movie?, context: Context) {
        binding.movieTitle.text = movie?.title
        binding.movieReleaseDate.text =  movie?.releaseDate
        val moviePosterURL = ApiService.POSTER_BASE_URL + movie?.posterPath

        Glide.with(itemView.context)
            .load(moviePosterURL)
            .into(binding.movieImage)

        itemView.setOnClickListener{
            val movieId: Int = movie?.id!!
            val action =
                MovieFragmentDirections.actionMovieFragmentToMovieDetailsFragment(
                    movieId
                )
            itemView.findNavController().navigate(action)
        }
    }
}