package com.example.dellin.room


import com.example.dellin.DellinApplication
import com.example.dellin.TerminalsParsed

class RoomRepository {
    private val db= DellinApplication.instance?.database
    fun insertIntoDatabase( arr:Array<TerminalsParsed>){
        db?.terminalsDao()?.insert(*arr)
    }
    fun deleteFromDatabase(item: TerminalsParsed)
    {
        db?.terminalsDao()?.delete(item)
    }
    fun getAllTerminals():Array<TerminalsParsed?>?
    {
        return db?.terminalsDao()?.getAllTerminals()
    }

}