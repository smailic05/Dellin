package com.example.dellin.ui.main


import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.room.Room
import com.example.dellin.AppDatabase
import com.example.dellin.ResponseTerminal
import com.example.dellin.TerminalApi
import com.example.dellin.TerminalsParsed
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainViewModel : ViewModel() {
    var arrayOfTerminals= arrayListOf<TerminalsParsed?>()

    fun createRequest(baseUrl:String) {
        val retrofit= Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).build()
        val routeApi = retrofit.create(TerminalApi::class.java)
        val routeResponse: Call<ResponseTerminal?>? = routeApi.getRoute()
        checkResponse(routeResponse)
    }

    private fun checkResponse(routeResponse: Call<ResponseTerminal?>?) {
        routeResponse?.enqueue(object : Callback<ResponseTerminal?> {
            override fun onResponse(call:  Call<ResponseTerminal?>?, response: Response<ResponseTerminal?>) {
                for (city in response.body()?.city!!)
                    for (terminal in city?.terminals?.terminal!!)
                        if (terminal != null) {
                            arrayOfTerminals.add(TerminalsParsed(terminal.name,terminal.address,
                                terminal.latitude,terminal.longitude,terminal.receiveCargo,terminal.giveoutCargo,
                                terminal.jsonMemberDefault,/*terminal.worktables,*/
                                terminal.maps?.width?.jsonMember640?.url
                            ) )
                        }
            }

            override fun onFailure(call:  Call<ResponseTerminal?>?, t: Throwable) {

            }

        })
    }
}