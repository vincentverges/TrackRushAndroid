package com.example.trackrush.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
     private const val BASE_URL = "https://api.openf1.org/v1/"

     val api: F1ApiService by lazy {
          Retrofit.Builder()
               .baseUrl(BASE_URL)
               .addConverterFactory(GsonConverterFactory.create())
               .build()
               .create(F1ApiService::class.java)
     }
}