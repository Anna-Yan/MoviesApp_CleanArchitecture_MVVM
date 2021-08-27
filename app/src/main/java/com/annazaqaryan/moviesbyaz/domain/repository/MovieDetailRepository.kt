package com.annazaqaryan.moviesbyaz.domain.repository

import com.annazaqaryan.moviesbyaz.domain.model.MovieDetailsResponse
import kotlinx.coroutines.flow.Flow

interface MovieDetailRepository {

    suspend fun getMovieDetails(movieId: Int): Flow<SingleResult<MovieDetailsResponse>>

}