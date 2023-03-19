package com.example.sanber_kotlin.api

import com.example.sanber_kotlin.data.ResponseCat
import retrofit2.Call
import retrofit2.http.GET

interface APIService {
    @GET("breeds")
    fun getBreeds() : Call<ResponseCat>
}