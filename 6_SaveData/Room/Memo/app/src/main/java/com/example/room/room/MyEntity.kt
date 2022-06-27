package com.example.room.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.room.room.Constants.COLUMN_DATE
import com.example.room.room.Constants.DB_NAME

@Entity(tableName = DB_NAME)
class MyEntity {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    var no: Long? = null

    @ColumnInfo
    var content: String = ""

    @ColumnInfo(name = COLUMN_DATE)
    var datetime: Long = 0

    constructor(no: Long?=null, content: String, datetime: Long) {
        this.no = no
        this.content = content
        this.datetime = datetime
    }
}