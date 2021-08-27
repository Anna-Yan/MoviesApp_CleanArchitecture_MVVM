package com.annazaqaryan.moviesbyaz.di

import com.annazaqaryan.moviesbyaz.presentation.details.MovieDetailViewModel
import com.annazaqaryan.moviesbyaz.presentation.movies.MovieViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val AppModule = module {

    viewModel { MovieViewModel(get()) }

    viewModel { MovieDetailViewModel(get()) }

    single { createGetMoviesUseCase(get()) }

    single { createGetMovieDetailsUseCase(get()) }

    single { createMovieRepository(get()) }

    single { createMovieDetailsRepository(get()) }
}