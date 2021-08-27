package com.annazaqaryan.moviesbyaz.domain.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object RetrofitClient {
    private var retrofitJson: Retrofit? = null
    private const val BASE_URL = "https://api.themoviedb.org/3/"
    private const val TIME_OUT = 30L

    fun createRetrofit(): Retrofit {
        if (retrofitJson == null) {
            retrofitJson = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(createOkHttpClient())
                .build()
        }
        return retrofitJson!!
    }

     fun createOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()

        builder.addInterceptor(MovieInterceptor())
        builder.readTimeout(TIME_OUT, TimeUnit.SECONDS)
        builder.writeTimeout(TIME_OUT, TimeUnit.SECONDS)

        return builder.build()
    }
}