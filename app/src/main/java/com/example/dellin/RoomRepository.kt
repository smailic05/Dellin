package com.example.dellin


import com.example.dellin.room.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RoomRepository {
    private val db=Dellin.instance?.getDatabase()
    fun insertIntoDatabase( arr:Array<TerminalsParsed>){
        db?.terminalsDao()?.insert(*arr)
    }
    fun deleteFromDatabase(item:TerminalsParsed)
    {
        db?.terminalsDao()?.delete(item)
    }
    fun getAllTerminals():Array<TerminalsParsed?>?
    {
        return db?.terminalsDao()?.getAllTerminals()
    }

}