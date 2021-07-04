package com.example.dellin.room

import androidx.room.*
import com.example.dellin.TerminalsParsed

@Dao
interface TerminalsDao {

        @Insert(onConflict = OnConflictStrategy.IGNORE)
        fun insert(vararg terminals: TerminalsParsed?)

        @Delete
        fun delete(terminals: TerminalsParsed?)

        @Query("SELECT * FROM terminalsparsed ORDER BY name ASC")
        fun getAllTerminals(): Array<TerminalsParsed?>?

        @Insert
        fun insertOrder(order: Order)

        @Query("SELECT * FROM orders")
        fun getAllOrders(): Array<Order?>?

}