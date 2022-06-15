package com.example.roomdaodatabase.data

import com.example.roomdaodatabase.data.db.Database
import com.example.roomdaodatabase.data.models.AddressAndSupplier

class SuppliersListRepository {

    suspend fun getAddressAndSupplier(): List<AddressAndSupplier>{
        return Database.instanse.addressDao().getAddressAndSupplier()
    }
}