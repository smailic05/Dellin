package com.example.dellin.room

import androidx.room.*
import com.example.dellin.TerminalsParsed

@Dao
interface TerminalsDao {

        // Добавление Person в бд
        @Insert(onConflict = OnConflictStrategy.IGNORE)
        fun insert(vararg terminals: TerminalsParsed?)

        // Удаление Person из бд
        @Delete
        fun delete(terminals: TerminalsParsed?)

        // Получение всех Person из бд
        @Query("SELECT * FROM terminalsparsed ORDER BY name ASC")
        fun getAllTerminals(): Array<TerminalsParsed?>?

}