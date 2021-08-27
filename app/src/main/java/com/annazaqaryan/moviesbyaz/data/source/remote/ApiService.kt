package com.annazaqaryan.moviesbyaz.data.source.remote

import com.annazaqaryan.moviesbyaz.domain.model.MovieDetailsResponse
import com.annazaqaryan.moviesbyaz.domain.model.MoviesResponse
import retrofit2.http.*

interface ApiService {

    // https://api.themoviedb.org/3/
    // https://api.themoviedb.org/3/movie/popular?api_key=86a65e3a555c72aeb86f95db1aa58031&page=1
    // https://api.themoviedb.org/3/movie/500?api_key=86a65e3a555c72aeb86f95db1aa58031

    companion object {
        const val MOVIES_API_KEY = "86a65e3a555c72aeb86f95db1aa58031"
        const val POSTER_BASE_URL = "https://image.tmdb.org/t/p/w342"
        const val FIRST_PAGE = 1
        const val POST_PER_PAGE = 20
    }


    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("page") page: Int): MoviesResponse

    @GET("movie/{id}")
    suspend fun getMovieDetails(@Path("id") id: Int): MovieDetailsResponse

}