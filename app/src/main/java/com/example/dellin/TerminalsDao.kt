package com.example.dellin

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.dellin.TerminalsParsed


interface TerminalsDao {

        // Добавление Person в бд
        @Insert
        fun insertAll(vararg terminals: TerminalsParsed?)

        // Удаление Person из бд
        @Delete
        fun delete(terminals: TerminalsParsed?)

        // Получение всех Person из бд
        @Query("SELECT * FROM terminalsparsed")
        fun getAllTerminals(): List<TerminalsParsed?>?

}