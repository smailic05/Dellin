package com.example.dellin
import com.example.dellin.AppDatabase

class TerminalsRepository(private val apiHelper: ApiHelper) {

    suspend fun createRequest() = apiHelper.getTerminals()

}