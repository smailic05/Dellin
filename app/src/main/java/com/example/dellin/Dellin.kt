package com.example.dellin

import android.app.Application
import android.location.Location
import androidx.room.Room
import com.example.dellin.room.AppDatabase


class Dellin:Application() {

    companion object{

        var location: Location?=null
        //Чтобы иметь единственный экземпляр БД
        var instance: Dellin? = null
    }

    var database: AppDatabase? = null
    private set

    override fun onCreate() {
        super.onCreate()
        instance = this
        database = Room.databaseBuilder(this, AppDatabase::class.java, "mydatabase")
            .build()
    }



}