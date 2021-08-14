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
import java.io.IOException

class MainViewModel: ViewModel() {

    private val repositoryRetrofit: TerminalsRepository = TerminalsRepository()
    private val roomRepository= RoomRepository()
    private val db= DellinApplication.instance?.database
    //массив терминалов  из базы данных
    private var _arrayOfTerminalsParsed= MutableLiveData<Array<TerminalsParsed?>>()
    val arrayOfTerminalsParsed
        get()=_arrayOfTerminalsParsed

    var firstTerminal=MutableLiveData<TerminalsParsed?>()
    var secondTerminal=MutableLiveData<TerminalsParsed?>()

    private val _snackbar= MutableLiveData<String>()
    val snackbar:LiveData<String>
        get() = _snackbar

    fun createRequest() = viewModelScope.launch(Dispatchers.IO){
        try {
            updateArrayOfTerminals()
            val temp = repositoryRetrofit.createRequest()
            saveTerminalsToDatabase(temp)
            updateArrayOfTerminals()
        }
        catch (exception: HttpException) {
            _snackbar.value="Error occurred"
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