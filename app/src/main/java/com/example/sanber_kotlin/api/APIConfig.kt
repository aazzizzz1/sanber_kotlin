package com.example.sanber_kotlin.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object APIConfig {

    const val baseUrl = "https://catfact.ninja/"

    fun getRetrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getService() : APIService {
        return getRetrofit().create(APIService::class.java)
    }
}