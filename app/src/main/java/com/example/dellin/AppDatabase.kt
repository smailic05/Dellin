package com.example.dellin

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [TerminalsParsed::class], version = 1)
abstract  class AppDatabase:RoomDatabase() {
    abstract fun terminalsDao(): TerminalsDao
}