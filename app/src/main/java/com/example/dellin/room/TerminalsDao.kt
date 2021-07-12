package com.example.dellin.room

import androidx.room.*
import com.example.dellin.TerminalsParsed
//Методы доступа к базе данных
@Dao
interface TerminalsDao {
        //методы получения терминалов
        @Insert(onConflict = OnConflictStrategy.IGNORE)
        fun insert(vararg terminals: TerminalsParsed?)

        @Delete
        fun delete(terminals: TerminalsParsed?)

        @Query("SELECT * FROM terminalsparsed ORDER BY name ASC")
        fun getAllTerminals(): Array<TerminalsParsed?>?

        //методы сохранения заказов
        @Insert(onConflict = OnConflictStrategy.IGNORE)
        fun insertOrder(order: Order)

        @Query("SELECT * FROM orders")
        fun getAllOrders(): Array<Order?>?

}