package com.example.roomdaodatabase.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = SupplierContract.TABLE_NAME)
data class Supplier(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = SupplierContract.Columns.ID)
    val id: Long,
    @ColumnInfo(name = SupplierContract.Columns.TITLE)
    val title: String,
    @ColumnInfo(name = SupplierContract.Columns.ADDRESS_ID)
    val addressId: Long
)
