package com.example.dellin.viewModel


import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dellin.DellinApplication
import com.example.dellin.TerminalsParsed
import com.example.dellin.retrofit.TerminalsRepository
import com.example.dellin.room.Order
import com.example.dellin.room.RoomRepository
//import com.example.dellin.room.Order
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import retrofit2.Retrofit

class MainViewModel: ViewModel() {

    private val repositoryRetrofit: TerminalsRepository = TerminalsRepository()
    private val roomRepository= RoomRepository()
    private val db= DellinApplication.instance?.database
    //массив терминалов  из базы данных
    private var _arrayOfTerminalsParsed= MutableLiveData<Array<TerminalsParsed?>>()
    val arrayOfTerminalsParsed
        get()=_arrayOfTerminalsParsed


    fun createRequest() = viewModelScope.launch(Dispatchers.IO){
        try {
            updateArrayOfTerminals()
            saveTerminalsToDatabase(repositoryRetrofit.createRequest())
            updateArrayOfTerminals()
        }
        catch (exception: HttpException) {
            Toast.makeText(DellinApplication.instance, exception.message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun saveTerminalsToDatabase(arr:Array<TerminalsParsed>) {
        roomRepository.insertIntoDatabase(arr)
    }
    fun saveOrder(firstTerminals: TerminalsParsed?, secondTerminals: TerminalsParsed?) =viewModelScope.launch(Dispatchers.IO) {
        db?.terminalsDao()?.insertOrder(Order( firstTerminals!!,secondTerminals!!))

    }
    private fun updateArrayOfTerminals()
    {
        arrayOfTerminalsParsed.postValue(roomRepository.getAllTerminals())
    }
}