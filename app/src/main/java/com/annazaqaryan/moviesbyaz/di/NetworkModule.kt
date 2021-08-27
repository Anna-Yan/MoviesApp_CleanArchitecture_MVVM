package com.annazaqaryan.moviesbyaz.di

import com.annazaqaryan.moviesbyaz.domain.repository.MoviesRepository
import com.annazaqaryan.moviesbyaz.domain.usecase.GetMoviesUseCase
import com.annazaqaryan.moviesbyaz.data.source.remote.ApiService
import com.annazaqaryan.moviesbyaz.domain.api.RetrofitClient
import com.annazaqaryan.moviesbyaz.data.repository.detail.MovieDetailsRepositoryImpl
import com.annazaqaryan.moviesbyaz.data.repository.movies.MovieListRepositoryImpl
import com.annazaqaryan.moviesbyaz.domain.repository.MovieDetailRepository
import com.annazaqaryan.moviesbyaz.domain.usecase.GetMovieDetailUseCase
import org.koin.dsl.module
import retrofit2.Retrofit


val NetworkModule = module {

    single { createService(get()) }

    single { RetrofitClient.createOkHttpClient() }

    single { RetrofitClient.createRetrofit() }

}

fun createService(retrofit: Retrofit): ApiService {
    return retrofit.create(ApiService::class.java)
}

//Movie repo
fun createMovieRepository(apiService: ApiService): MoviesRepository {
    return MovieListRepositoryImpl(apiService)
}

//Movie detail repo
fun createMovieDetailsRepository(apiService: ApiService): MovieDetailRepository{
    return MovieDetailsRepositoryImpl(apiService)
}

//Movie useCase
fun createGetMoviesUseCase(movieRepository: MoviesRepository): GetMoviesUseCase {
    return GetMoviesUseCase(movieRepository)
}

//Movie Details useCase
fun createGetMovieDetailsUseCase(movieDetailRepository: MovieDetailRepository): GetMovieDetailUseCase {
    return GetMovieDetailUseCase(movieDetailRepository)
}