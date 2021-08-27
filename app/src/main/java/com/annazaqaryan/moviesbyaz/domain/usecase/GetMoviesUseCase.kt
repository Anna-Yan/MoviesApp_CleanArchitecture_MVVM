package com.annazaqaryan.moviesbyaz.domain.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import com.annazaqaryan.moviesbyaz.domain.usecase.base.UseCase
import com.annazaqaryan.moviesbyaz.domain.model.Movie
import com.annazaqaryan.moviesbyaz.domain.repository.SingleResult
import com.annazaqaryan.moviesbyaz.domain.repository.MoviesRepository

class GetMoviesUseCase constructor(
    private val moviesRepository: MoviesRepository
) : UseCase<LiveData<SingleResult<List<Movie>>>, Any?>() {

    override suspend fun run(params: Any?): LiveData<SingleResult<List<Movie>>> {
        return liveData {
            emitSource(moviesRepository.getMoviesList().asLiveData())
        }
    }

}