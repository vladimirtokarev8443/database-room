package com.example.roomdaodatabase.data

import android.content.Context
import androidx.room.Room
import com.example.roomdaodatabase.MIGRATION_1_2

object Database {

    lateinit var instanse: StoreChainDatabase
        private set

    fun init(context: Context) {
        instanse = Room.databaseBuilder(
            context,
            StoreChainDatabase::class.java,
            StoreChainDatabase.DB_NAME
        )
            .addMigrations(MIGRATION_1_2)
            .build()
    }
}