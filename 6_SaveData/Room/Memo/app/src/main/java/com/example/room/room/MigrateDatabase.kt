package com.example.room.room

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.room.room.Constants.DB_NAME

class MigrateDatabase {
    companion object {
        val MIGRATE_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                val alter = "ALTER TABLE $DB_NAME ADD COLUMN new_title text"
                database.execSQL(alter)
            }
        }
    }
}