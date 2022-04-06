package com.example.room.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MyEntity::class], version = 1, exportSchema = false)
abstract class MyDatabase: RoomDatabase() {
    abstract fun myDao(): MyDao
}