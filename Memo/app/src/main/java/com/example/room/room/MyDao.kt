package com.example.room.room

import androidx.room.*

@Dao
interface MyDao {
    @Query("SELECT * FROM room_table")
    fun getAll(): List<MyEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(table: MyEntity)

    @Delete
    fun delete(table: MyEntity)

    @Update
    fun update(table: MyEntity)
}