package com.example.room.room

import android.content.Context
import androidx.room.Room
import com.example.room.room.Constants.DB_NAME

object DatabaseProvider {
    fun provideDB(applicationContext: Context) = Room.databaseBuilder(
        applicationContext,
        MyRoomHelper::class.java,
        DB_NAME
    )
    .addMigrations(MigrateDatabase.MIGRATE_1_2)
    .allowMainThreadQueries()
    .build()
}