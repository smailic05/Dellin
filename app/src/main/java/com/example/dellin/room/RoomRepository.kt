package com.example.dellin.room


import com.example.dellin.Dellin
import com.example.dellin.TerminalsParsed

class RoomRepository {
    private val db= Dellin.instance?.getDatabase()
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