package com.example.dellin.retrofit
import com.example.dellin.Dellin
import com.example.dellin.TerminalsParsed
import com.example.dellin.convert
import com.example.dellin.retrofit.RetrofitBuilder
import com.example.dellin.retrofit.TerminalApi
import kotlin.math.pow

class TerminalsRepository {
    var terminal: TerminalApi = RetrofitBuilder.apiService

    suspend fun createRequest() :Array<TerminalsParsed>
    {
        val listOfTerminals= mutableListOf<TerminalsParsed>()
        val response=terminal.getRoute()
        for (city in response?.city!!)
            for (terminal in city?.terminals?.terminal!!)
                if (terminal != null) {
                    listOfTerminals.add(
                        TerminalsParsed(terminal.id!!.toInt(),terminal.name!!,terminal.address,
                            terminal.latitude,terminal.longitude,terminal.receiveCargo,terminal.giveoutCargo,
                            terminal.jsonMemberDefault,terminal.worktables?.convert(),
                            terminal.maps?.width?.jsonMember640?.url
                        ) )
                        //TODO convert to liveData
                    }
        return listOfTerminals.toTypedArray()
    }
}