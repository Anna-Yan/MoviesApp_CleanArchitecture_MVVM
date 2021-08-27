package com.annazaqaryan.moviesbyaz.domain.repository

import com.annazaqaryan.moviesbyaz.domain.exception.InvalidRequestException
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import retrofit2.HttpException
import java.io.IOException

suspend fun <T: Any> ConflatedBroadcastChannel<SingleResult<T>>.baseOffer(getResponse: suspend () -> T){
    try {
        val response = getResponse()
        send(SingleResult.Success(response))
    } catch (exception: IOException) {
        send(SingleResult.Error(exception))
    } catch (exception: HttpException) {
        send(
            SingleResult.Error(com.annazaqaryan.moviesbyaz.domain.exception.HttpException(
            exception.response()?.run { raw().request.url.toString()} ?:"",
            exception.code(), exception.message())))
    } catch (t: Throwable){
        send(SingleResult.Error(InvalidRequestException(t)))
    }
}

fun <T: Any> ConflatedBroadcastChannel<SingleResult<T>>.asFlow(): Flow<SingleResult<T>> {
    return flowOf(value)
}