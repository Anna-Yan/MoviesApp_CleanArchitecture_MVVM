package com.annazaqaryan.moviesbyaz.data.repository.movies

import com.annazaqaryan.moviesbyaz.data.source.remote.ApiService
import com.annazaqaryan.moviesbyaz.domain.model.Movie
import com.annazaqaryan.moviesbyaz.domain.repository.SingleResult
import com.annazaqaryan.moviesbyaz.domain.repository.baseOffer
import com.annazaqaryan.moviesbyaz.domain.repository.MoviesRepository
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow

open class MovieListRepositoryImpl(private val apiService: ApiService) : MoviesRepository {

    private val moviesResult = ConflatedBroadcastChannel<SingleResult<List<Movie>>>()


    override suspend fun getMoviesList(): Flow<SingleResult<List<Movie>>> {
        getMovies()
        return moviesResult.asFlow()
    }

    private suspend fun getMovies() {
        moviesResult.baseOffer {
            apiService.getPopularMovies(1).moviesList
        }
    }

}