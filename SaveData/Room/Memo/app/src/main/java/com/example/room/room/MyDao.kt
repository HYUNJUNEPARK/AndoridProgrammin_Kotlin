package com.example.room.room

import androidx.room.*
import com.example.room.room.Constants.DB_NAME

@Dao
interface MyDao {
    @Query("SELECT * FROM $DB_NAME")
    fun getAll(): List<MyEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(table: MyEntity)

    @Delete
    fun delete(table: MyEntity)

    @Update
    fun update(table: MyEntity)
}