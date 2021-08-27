package com.annazaqaryan.moviesbyaz.domain.repository

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
 */
sealed class SingleResult<out T : Any> {

    data class Success<out T : Any>(val data: T) : SingleResult<T>()

    data class Error(val exception: Exception) : SingleResult<Nothing>()
}