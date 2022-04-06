package com.example.room.room

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

class MigrateDatabase {
    companion object {
        val MIGRATE_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                val alter = "ALTER TABLE room_table ADD COLUMN new_title text"
                database.execSQL(alter)
            }
        }
    }
}