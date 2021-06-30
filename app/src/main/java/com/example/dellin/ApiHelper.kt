package com.example.dellin

class ApiHelper(private val terminalApi: TerminalApi) {
    suspend fun getTerminals()=terminalApi.getRoute()
}