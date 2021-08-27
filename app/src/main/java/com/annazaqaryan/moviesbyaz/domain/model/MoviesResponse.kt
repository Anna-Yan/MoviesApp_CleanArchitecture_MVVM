package com.annazaqaryan.moviesbyaz.domain.model

import com.google.gson.annotations.SerializedName

data class MoviesResponse(
    @SerializedName("page") val page: Int,
    @SerializedName("total_pages") val pagesTotal: Int,
    @SerializedName("results") val moviesList: List<Movie>
)