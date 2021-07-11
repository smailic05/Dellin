package com.example.dellin.viewModel


import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dellin.Dellin
import com.example.dellin.TerminalsParsed
import com.example.dellin.retrofit.TerminalsRepository
import com.example.dellin.room.Order
import com.example.dellin.room.RoomRepository
//import com.example.dellin.room.Order
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val repository: TerminalsRepository = TerminalsRepository()
    private val roomRepository= RoomRepository()
    private val db= Dellin.instance?.database
    var array:Array<TerminalsParsed?>?=null


    fun createRequest() = viewModelScope.launch(Dispatchers.IO){
        try {
            save(repository.createRequest())
            updateArray()
        }
        catch (exception: Exception) {
            Toast.makeText(Dellin.instance, exception.message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun save(arr:Array<TerminalsParsed>) {
        roomRepository.insertIntoDatabase(arr)
    }
    fun saveOrder(firstTerminals: TerminalsParsed?, secondTerminals: TerminalsParsed?) =viewModelScope.launch(Dispatchers.IO) {
        db?.terminalsDao()?.insertOrder(Order( firstTerminals!!,secondTerminals!!))

    }
    private fun updateArray()
    {
        array= roomRepository.getAllTerminals()
    }
}