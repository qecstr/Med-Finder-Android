package com.example.medfinder.network

import com.example.medfinder.model.MedObject
import com.example.medfinder.model.Meds
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.converter.gson.GsonConverterFactory

import retrofit2.http.GET
import java.util.concurrent.TimeUnit



interface MedsApiService {

    @GET("/v4/spreadsheets/1VEA_bLxt76K2t3b_fnXqcQcAlIGtKpbFUzhs4OkmMy4/values/Sheet1!A2:B6?key=AIzaSyAsXkF_dIpWcaVhoiZUtxth6tzjU9pSuPQ")
    fun getMeds(): Call<MedObject>
}
internal object MedsApi {
    lateinit var retrofit: Retrofit

    val client: Retrofit
        get() {

            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .connectTimeout(2, TimeUnit.MINUTES)
                .readTimeout(2, TimeUnit.MINUTES)
                .build()


            retrofit = Retrofit.Builder()
                .baseUrl("https://sheets.googleapis.com")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()

            return retrofit
        }
}