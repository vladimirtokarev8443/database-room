package com.example.roomdaodatabase.app

import android.app.Application
import com.example.roomdaodatabase.data.Database

class DatabaseApp: Application() {

    override fun onCreate() {
        super.onCreate()
        Database.init(this)

    }
}