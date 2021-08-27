package com.annazaqaryan.moviesbyaz.domain.api

import com.annazaqaryan.moviesbyaz.data.source.remote.ApiService.Companion.MOVIES_API_KEY
import okhttp3.Interceptor
import okhttp3.Response


class MovieInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val url = request.url.newBuilder().addQueryParameter("api_key", MOVIES_API_KEY).
            addQueryParameter("mode", "json").
            addQueryParameter("units", "metric").build()
        request = request.newBuilder().url(url).build()
        return chain.proceed(request)
    }

}