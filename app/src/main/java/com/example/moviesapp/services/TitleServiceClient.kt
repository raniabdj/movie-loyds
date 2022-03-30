package com.example.moviesapp.services

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
const val API_KEY2="k_1glq0o5b"

object TitleServiceClient {

    fun getClient(): TitlesService{
        val requestInterceptor = Interceptor { chain ->
            val url = chain.request().url
                .newBuilder()
                .addQueryParameter("api_key",API_KEY2)
                .build()

            val request = chain.request()
                .newBuilder()
                .url(url)
                .build()
            return@Interceptor chain.proceed(request)


        }
        val okHttpClient : OkHttpClient =  OkHttpClient.Builder()
            .addInterceptor(requestInterceptor)
            .connectTimeout(60, TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TitlesService::class.java)
    }
}