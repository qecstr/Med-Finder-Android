  package com.example.medfinder.network

import com.example.medfinder.model.MedObject
import com.example.medfinder.model.Meds

import retrofit2.Retrofit
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory

import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.converter.gson.GsonConverterFactory

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.concurrent.TimeUnit
  private const val BASE_URL =
      "https://sheets.googleapis.com"

  private val retrofit = Retrofit.Builder()
      .addConverterFactory(GsonConverterFactory.create())
      .baseUrl(BASE_URL)
      .build()



interface MedsApiService {

        @GET("/v4/spreadsheets/1VEA_bLxt76K2t3b_fnXqcQcAlIGtKpbFUzhs4OkmMy4/values/A1:H1347?key=AIzaSyAsXkF_dIpWcaVhoiZUtxth6tzjU9pSuPQ")
    suspend fun getValues(
        /*@Path("spreadsheetId") spreadsheetId:String,
        @Query("key") key:String?*/
    ): MedObject
}
object MedsApi {
    val retrofitService: MedsApiService by lazy {
        retrofit.create(MedsApiService::class.java)
    }
}