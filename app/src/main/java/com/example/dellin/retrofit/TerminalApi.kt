package com.example.dellin.retrofit

import com.example.dellin.ResponseTerminal
import retrofit2.http.GET

public interface TerminalApi {
    @GET("/static/catalog/terminals_v3.json")
    suspend fun getRoute(): ResponseTerminal?
}