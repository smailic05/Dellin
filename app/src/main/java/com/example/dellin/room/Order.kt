package com.example.dellin.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.dellin.TerminalsParsed
import java.util.*
import java.util.stream.Collectors


@Entity(tableName = "order")
data class Order (@PrimaryKey
                  //@TypeConverters(HobbiesConverter::class)
                  var outTerminal: TerminalsParsed,
//                  @TypeConverters(HobbiesConverter::class)
                  var inTerminal: TerminalsParsed)

//class HobbiesConverter {
//    @TypeConverter
//    fun fromHobbies(data: String): TerminalsParsed {
//        return unpack(data)
//    }
//
//    @TypeConverter
//    fun toHobbies(data: TerminalsParsed): String {
//        return data.pack()
//    }
//    fun unpack(string: String):TerminalsParsed
//    {
//        val arr= string.split(",")
//        return TerminalsParsed(arr[0].toInt(),arr[0],arr[0],arr[0],arr[0],arr[0].toBoolean(),
//            arr[0].toBoolean(),arr[0].toBoolean(),arr[0])
//    }
//}