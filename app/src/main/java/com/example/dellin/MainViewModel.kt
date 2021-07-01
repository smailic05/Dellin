package com.example.dellin


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dellin.*
import com.example.dellin.retrofit.TerminalsRepository
import com.example.dellin.room.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val repository: TerminalsRepository = TerminalsRepository()
    private val roomRepository=RoomRepository()

    var array:Array<TerminalsParsed?>?=null
    fun createRequest() = viewModelScope.launch(Dispatchers.IO){
        try {
            val arr=repository.createRequest()
            save(arr)
            updateArray()
        }
        catch (exception: Exception) {
            val q=exception.message
        }
    }

    private fun save(arr:Array<TerminalsParsed>) {
        roomRepository.insertIntoDatabase(arr)
    }
    fun updateArray()
    {
        array= roomRepository.getAllTerminals()
    }
}