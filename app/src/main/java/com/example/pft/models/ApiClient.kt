package com.example.pft.models

import ReadConfig
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private val config = ReadConfig()
    private val BASE_URL = "${config.getServerUrl()}/api/"

    val gson = GsonBuilder().setLenient().create()

    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    val apiService: ApiService = retrofit.create(ApiService::class.java)
}