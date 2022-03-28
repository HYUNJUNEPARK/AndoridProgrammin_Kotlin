package com.example.sqlite.sqlite

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.sqlite.activity.MainActivity.Companion.COLUMN_CONTENT
import com.example.sqlite.activity.MainActivity.Companion.COLUMN_DATETIME
import com.example.sqlite.activity.MainActivity.Companion.COLUMN_NO
import com.example.sqlite.activity.MainActivity.Companion.DATABASE_NAME
import com.example.sqlite.model.Memo

class SQLiteHelper(context: Context, name: String, version: Int) : SQLiteOpenHelper(context, name, null, version) {
    override fun onCreate(db: SQLiteDatabase?) {
        val create = "CREATE TABLE memo (" +
                "no INTEGER PRIMARY KEY, " +
                "content TEXT, " +
                "datetime INTEGER" +
                ")"
        db?.execSQL(create)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }

    fun insertMemo(memo: Memo) {
        val values = ContentValues()
        values.put(COLUMN_CONTENT, memo.content)
        values.put(COLUMN_DATETIME, memo.datetime)
        val db = writableDatabase
        db.insert(DATABASE_NAME, null, values)
        db.close()
    }

    fun selectMemo(): MutableList<Memo> {
        val memoList = mutableListOf<Memo>()
        val readQuery = "SELECT * FROM memo"
        val db = readableDatabase
        val cursor = db.rawQuery(readQuery, null)

        while (cursor.moveToNext()) {
            val no = cursor.getLong(cursor.getColumnIndex(COLUMN_NO))
            val content = cursor.getString(cursor.getColumnIndex(COLUMN_CONTENT))
            val datetime = cursor.getLong(cursor.getColumnIndex(COLUMN_DATETIME))
            memoList.add(Memo(no, content, datetime))
        }
        cursor.close()
        db.close()
        return memoList
    }

    fun updateMemo(memo: Memo) {
        val values = ContentValues()
        values.put(COLUMN_CONTENT, memo.content)
        values.put(COLUMN_DATETIME, memo.datetime)
        val db = writableDatabase
        db.update(DATABASE_NAME, values, "no = ${memo.no}", null)
        //wd.update("memo", values, "no = ?", arrayOf("${memo.no}"))
        db.close()
    }

    fun deleteMemo(memo: Memo) {
        val deleteQuery = "DELETE FROM memo WHERE no = ${memo.no}"
        val db = writableDatabase
        db.execSQL(deleteQuery)
        db.close()
    }
}

