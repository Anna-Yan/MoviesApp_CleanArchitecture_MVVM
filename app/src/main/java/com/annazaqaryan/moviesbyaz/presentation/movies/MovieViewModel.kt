package com.annazaqaryan.moviesbyaz.presentation.movies

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.annazaqaryan.moviesbyaz.domain.exception.ApiError
import com.annazaqaryan.moviesbyaz.domain.model.Movie
import com.annazaqaryan.moviesbyaz.domain.repository.SingleResult
import com.annazaqaryan.moviesbyaz.domain.usecase.GetMoviesUseCase
import com.annazaqaryan.moviesbyaz.domain.usecase.base.UseCaseResponse
import com.annazaqaryan.moviesbyaz.presentation.BaseViewModel

class MovieViewModel(
    private val moviesUseCase: GetMoviesUseCase
) : BaseViewModel() {

    lateinit var moviesDataList: LiveData<SingleResult<List<Movie>>>

    fun getMoviesList() {
        moviesUseCase.invoke(
            viewModelScope, null,
            object : UseCaseResponse<LiveData<SingleResult<List<Movie>>>> {
                override fun onSuccess(result: LiveData<SingleResult<List<Movie>>>) {
                    Log.i(TAG, "result: $result")

                    moviesDataList = result
                }

                override fun onError(apiError: ApiError?) {
                    Log.i(TAG, "result:" + apiError?.getErrorMessage())

                }
            },
        )
    }

    companion object {
        private val TAG = MovieViewModel::class.java.name
    }
}