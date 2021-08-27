package com.annazaqaryan.moviesbyaz.domain.repository


import com.annazaqaryan.moviesbyaz.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {

    suspend fun getMoviesList(): Flow<SingleResult<List<Movie>>>
}