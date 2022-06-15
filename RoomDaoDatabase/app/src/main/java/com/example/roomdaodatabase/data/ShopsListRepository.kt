package com.example.roomdaodatabase.data

import com.example.roomdaodatabase.data.db.Database
import com.example.roomdaodatabase.data.models.AddressAndShop

class ShopsListRepository {
    suspend fun getAddressAndShop(): List<AddressAndShop>{
        return Database.instanse.addressDao().getAddressAndShop()
    }
}