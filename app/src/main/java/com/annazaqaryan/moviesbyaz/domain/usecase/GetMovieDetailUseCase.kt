package com.annazaqaryan.moviesbyaz.domain.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import com.annazaqaryan.moviesbyaz.domain.usecase.base.UseCase
import com.annazaqaryan.moviesbyaz.domain.model.MovieDetailsResponse
import com.annazaqaryan.moviesbyaz.domain.repository.MovieDetailRepository
import com.annazaqaryan.moviesbyaz.domain.repository.SingleResult

class GetMovieDetailUseCase constructor(
    private val movieDetailRepository: MovieDetailRepository
) : UseCase<LiveData<SingleResult<MovieDetailsResponse>>, Int>() {

    override suspend fun run(params: Int?): LiveData<SingleResult<MovieDetailsResponse>> {
        return liveData {
            emitSource(movieDetailRepository.getMovieDetails(params!!).asLiveData())
        }
    }

}