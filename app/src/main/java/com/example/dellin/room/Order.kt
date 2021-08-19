package com.example.dellin.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.dellin.TerminalsParsed
// Класс заказов, сохраняемых в БД
@Entity(tableName = "orders")
data class Order (@PrimaryKey(autoGenerate = true)
                  val id:Int,
                  @TypeConverters(HobbiesConverter::class)
                  var outTerminal: TerminalsParsed,
                  @TypeConverters(HobbiesConverter::class)
                  var inTerminal: TerminalsParsed)
{
    constructor (outTerminal: TerminalsParsed,
            inTerminal: TerminalsParsed):this(idDatabase++,outTerminal,inTerminal) {

    }
    companion object{
        var idDatabase=0
    }
}
// Конвертер класса Order для сохранения в БД
class HobbiesConverter {
    @TypeConverter
    fun fromTerminals(data: String): TerminalsParsed {
        return unpack(data)
    }

    @TypeConverter
    fun toTerminals(data: TerminalsParsed): String {
        return data.pack()
    }
    private fun unpack(string: String):TerminalsParsed
    {
        val arr= string.split("&")
        return TerminalsParsed(arr[0].toInt(),arr[1],arr[2],arr[3],arr[4],arr[5].toBoolean(),
            arr[6].toBoolean(),arr[7].toBoolean(),arr[8],arr[9])
    }

    fun TerminalsParsed.pack():String
    {
        return "$id&$name&$address&$latitude&$longitude&$receiveCargo&$giveoutCargo&$defaultTerminal&$worktable&$maps"
    }
}