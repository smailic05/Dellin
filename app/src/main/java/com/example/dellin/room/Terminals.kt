package com.example.dellin

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//data class WorktableParsed(
//
//    var sunday: String? = null,
//
//    var saturday: String? = null,
//
//    var tuesday: String? = null,
//
//    var wednesday: String? = null,
//
//    var thursday: String? = null,
//
//    var friday: String? = null,
//
//    var department: String? = null,
//
//    var monday: String? = null,
//
//    var timetable: String? = null
//)

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
                           @ColumnInfo(name = "maps")
                            var maps: String?)




