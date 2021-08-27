package com.annazaqaryan.moviesbyaz.data.repository.detail

import com.annazaqaryan.moviesbyaz.data.source.remote.ApiService
import com.annazaqaryan.moviesbyaz.domain.model.MovieDetailsResponse
import com.annazaqaryan.moviesbyaz.domain.repository.MovieDetailRepository
import com.annazaqaryan.moviesbyaz.domain.repository.SingleResult
import com.annazaqaryan.moviesbyaz.domain.repository.baseOffer
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow

class MovieDetailsRepositoryImpl(private val apiService: ApiService) : MovieDetailRepository {

    private val movieDetailsResult = ConflatedBroadcastChannel<SingleResult<MovieDetailsResponse>>()


    override suspend fun getMovieDetails(movieId: Int): Flow<SingleResult<MovieDetailsResponse>> {
        getDetails(movieId)
        return movieDetailsResult.asFlow()
    }

    private suspend fun getDetails(movieId: Int) {
        movieDetailsResult.baseOffer {
            apiService.getMovieDetails(movieId)
        }
    }

}