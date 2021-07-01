package com.example.dellin.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {
    private const val BASE_URL = "https://api.dellin.ru"

    private fun getRetrofit(): Retrofit{
        return Retrofit.Builder().baseUrl(BASE_URL).
        addConverterFactory(GsonConverterFactory.create()).build()
    }
    val apiService: TerminalApi = getRetrofit().create(TerminalApi::class.java)
}