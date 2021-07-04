package com.example.dellin


import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dellin.retrofit.TerminalsRepository
import com.example.dellin.room.RoomRepository
//import com.example.dellin.room.Order
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val repository: TerminalsRepository = TerminalsRepository()
    private val roomRepository= RoomRepository()
    //private val db=Dellin.instance?.getDatabase()
    companion object{
        var firstVisibility:Int= View.INVISIBLE
        var secondVisibility:Int= View.INVISIBLE
        var firstTerminals:TerminalsParsed?=null
        var secondTerminals:TerminalsParsed?=null
    }
    var array:Array<TerminalsParsed?>?=null
    fun createRequest() = viewModelScope.launch(Dispatchers.IO){
        try {
            val arr=repository.createRequest()
            save(arr)
            updateArray()
        }
        catch (exception: Exception) {
        }
    }

    private fun save(arr:Array<TerminalsParsed>) {
        roomRepository.insertIntoDatabase(arr)
    }
//    fun saveOrder()=viewModelScope.launch(Dispatchers.IO) {
//        db?.terminalsDao()?.insertOrder(Order( firstTerminals!!,secondTerminals!!))
//    }
    fun updateArray()
    {
        array= roomRepository.getAllTerminals()
    }
}