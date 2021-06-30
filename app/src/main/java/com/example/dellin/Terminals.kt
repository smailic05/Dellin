package com.example.dellin

import android.net.Uri
import androidx.room.Entity
import androidx.room.PrimaryKey
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


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

@Entity
data class TerminalsParsed(@PrimaryKey var name:String?,
                           var address: String?,
                           var latitude:String?, var longitude:String?,
                           var receiveCargo:Boolean?, var giveoutCargo:Boolean?,
                           var default:Boolean?,
                           /*var worktable:Worktables?,*/ var maps: String?)



