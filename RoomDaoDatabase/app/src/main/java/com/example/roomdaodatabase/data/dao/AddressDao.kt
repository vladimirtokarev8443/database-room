package com.example.roomdaodatabase.data.dao

import androidx.room.*
import com.example.roomdaodatabase.data.models.*

@Dao
interface AddressDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAdress(address: Address): Long

    @Transaction
    @Query("SELECT * FROM ${AddressContract.TABLE_NAME}")
    suspend fun getAddressAndShop(): List<AddressAndShop>

    @Transaction
    @Query("SELECT * FROM ${AddressContract.TABLE_NAME}")
    suspend fun getAddressAndEmployee(): List<AddressAndEmployee>

    @Transaction
    @Query("SELECT * FROM ${AddressContract.TABLE_NAME}")
    suspend fun getAddressAndSupplier(): List<AddressAndSupplier>

}