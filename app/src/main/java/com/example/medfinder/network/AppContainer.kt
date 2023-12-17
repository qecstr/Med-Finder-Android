package com.example.medfinder.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface AppContainer {
}
class DefaultAppContainer : AppContainer {
    private  val BASE_URL =
        " https://sheets.googleapis.com/auth/v4/"


    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    val retrofitService: MedsApiService by lazy {
        retrofit.create(MedsApiService::class.java)
    }
}