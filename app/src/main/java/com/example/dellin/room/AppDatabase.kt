package com.example.dellin.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.dellin.TerminalsParsed

// База данных приложения
@Database(entities = arrayOf(TerminalsParsed::class, Order::class), version = 1)
@TypeConverters(HobbiesConverter::class)
abstract  class AppDatabase:RoomDatabase() {
    abstract fun terminalsDao(): TerminalsDao
}