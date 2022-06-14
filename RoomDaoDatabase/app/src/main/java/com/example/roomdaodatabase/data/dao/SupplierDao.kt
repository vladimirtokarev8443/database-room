package com.example.roomdaodatabase.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.roomdaodatabase.data.models.Supplier
import com.example.roomdaodatabase.data.models.SupplierContract

@Dao
interface SupplierDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSupplier(supplier: Supplier)

    @Query("SELECT * FROM ${SupplierContract.TABLE_NAME}")
    suspend fun getAllSuppliers(): List<Supplier>

    @Query("SELECT ${SupplierContract.Columns.TITLE} FROM ${SupplierContract.TABLE_NAME} WHERE ${SupplierContract.Columns.ID}=:supplierId")
    suspend fun getTitleSupplierById(supplierId: Long): String
}