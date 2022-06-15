package com.example.roomdaodatabase

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.roomdaodatabase.enum.OrderStatus

val MIGRATION_1_2 = object : Migration(1, 2){
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE employees ADD COLUMN age INTEGER NOT NULL DEFAULT 0")
    }

}