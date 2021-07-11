package com.example.dellin

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*




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
                           @ColumnInfo(name = "worktable")
                           var worktable: String?,
                           @ColumnInfo(name = "maps")
                            var maps: String?)
{

    fun pack():String
    {
        return "$id,$name,$address,$latitude,$longitude,$receiveCargo,$giveoutCargo,$defaultTerminal,$worktable,$maps"
    }

}




