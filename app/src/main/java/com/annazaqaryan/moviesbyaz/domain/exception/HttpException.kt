package com.annazaqaryan.moviesbyaz.domain.exception

class HttpException(
    val url: String,
    val code: Int,
    message: String
) : RuntimeException(message)