package com.example.dellin

import retrofit2.Call
import retrofit2.http.GET

public interface TerminalApi {
    @GET("/static/catalog/terminals_v3.json")
    suspend fun getRoute(): Call<ResponseTerminal?>?
}