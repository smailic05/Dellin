package com.example.dellin.retrofit
import com.example.dellin.TerminalsParsed
import com.example.dellin.WorktableItem
import com.example.dellin.Worktables

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
                            terminal.maps?.width?.jsonMember640?.height?.jsonMember640?.url
                        ) )
                    }
        return listOfTerminals.toTypedArray()
    }
    private fun WorktableItem.convert():String
    {
        return "$department|$monday|$tuesday|$wednesday|$thursday|$friday|$saturday|$sunday|$timetable"
    }
    private fun Worktables.convert():String
    {
        var temp=""
        if (worktable != null) {
            for (item in worktable)
                temp+="&"+item?.convert()
        }
        return temp
    }

}