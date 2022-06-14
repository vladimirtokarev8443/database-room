package com.example.roomdaodatabase.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = ShopContract.TABLE_NAME)
data class Shop(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ShopContract.Columns.ID)
    val id: Long,
    @ColumnInfo(name = ShopContract.Columns.TITLE)
    val title: String,
    @ColumnInfo(name = ShopContract.Columns.ADDRESS_ID)
    val addressId: Long
)
