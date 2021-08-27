package com.annazaqaryan.moviesbyaz.presentation.details

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.annazaqaryan.moviesbyaz.domain.exception.ApiError
import com.annazaqaryan.moviesbyaz.domain.model.MovieDetailsResponse
import com.annazaqaryan.moviesbyaz.domain.repository.SingleResult
import com.annazaqaryan.moviesbyaz.domain.usecase.GetMovieDetailUseCase
import com.annazaqaryan.moviesbyaz.domain.usecase.base.UseCaseResponse
import com.annazaqaryan.moviesbyaz.presentation.BaseViewModel

class MovieDetailViewModel(private val movieDetailUseCase: GetMovieDetailUseCase) : BaseViewModel() {

    private var movieId = MutableLiveData<Int>()
    lateinit var movieDetails: LiveData<SingleResult<MovieDetailsResponse>>


    fun getMovieDetails() {
        movieDetailUseCase.invoke(
            viewModelScope, movieId.value,
            object : UseCaseResponse<LiveData<SingleResult<MovieDetailsResponse>>> {
                override fun onSuccess(result: LiveData<SingleResult<MovieDetailsResponse>>) {
                    Log.i(TAG, "result: $result")
                    movieDetails = result
                }

                override fun onError(apiError: ApiError?) {
                    Log.i(TAG, "result:" + apiError?.getErrorMessage())
                }
            },
        )
    }

    fun setMovieId(id: Int) {
        movieId.value = id
    }

    companion object {
        private val TAG = MovieDetailViewModel::class.java.name
    }
}