package com.example.dellin

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

fun WorktableItem.convert():String
{
    return "$department,$monday,$tuesday,$wednesday,$thursday,$friday,$saturday,$sunday,$timetable"
}
fun Worktables.convert():String
{
    var temp=""
    if (worktable != null) {
        for (item in worktable)
            temp+=item?.convert()+"&"
    }
    return temp
}

@Entity(tableName = "terminalsparsed")
data class TerminalsParsed(@PrimaryKey
                           @ColumnInfo(name = "id")
                           var id:Int,
                           @ColumnInfo(name = "name")
                           var name:String,
                           @ColumnInfo(name = "address")
                           var address: String?,
                           @ColumnInfo(name = "latitude")
                           var latitude:String?,
                           @ColumnInfo(name = "longitude")
                           var longitude:String?,
                           @ColumnInfo(name = "receiveCargo")
                           var receiveCargo:Boolean?,
                           @ColumnInfo(name = "giveoutCargo")
                           var giveoutCargo:Boolean?,
                           @ColumnInfo(name = "default")
                           var defaultTerminal:Boolean?,
                           var worktable: String?,
                           @ColumnInfo(name = "maps")
                            var maps: String?)
{

    fun pack():String
    {
        return "$id,$name,$address,$latitude,$longitude,$receiveCargo,$giveoutCargo,$defaultTerminal,$worktable,$maps"
    }

}




