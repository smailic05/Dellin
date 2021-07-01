package com.example.dellin.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.dellin.TerminalsParsed


@Database(entities = arrayOf(TerminalsParsed::class), version = 1)
abstract  class AppDatabase:RoomDatabase() {
    abstract fun terminalsDao(): TerminalsDao
}