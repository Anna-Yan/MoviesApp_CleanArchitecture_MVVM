package com.annazaqaryan.moviesbyaz.domain.usecase.base

import com.annazaqaryan.moviesbyaz.domain.exception.ApiError


interface UseCaseResponse<Type> {

    fun onSuccess(result: Type)

    fun onError(apiError: ApiError?)
}

