package com.example.dellin

import android.app.Application
import android.location.Location
import androidx.room.Room
import com.example.dellin.room.AppDatabase


class Dellin:Application() {

    companion object{
        lateinit var location: Location
        var instance: Dellin? = null
    }

    private var database: AppDatabase? = null

    override fun onCreate() {
        super.onCreate()
        instance = this
        database = Room.databaseBuilder(this, AppDatabase::class.java, "mydatabase")
            .build()
    }

    fun getInstance(): Dellin? {
        return instance
    }

    fun getDatabase(): AppDatabase? {
        return database
    }
}