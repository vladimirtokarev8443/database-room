package com.example.roomdaodatabase.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = AddressContract.TABLE_NAME,
    indices = [
        Index(AddressContract.Columns.ADDRESS, unique = true)
    ]
)
data class Address(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = AddressContract.Columns.ID)
    val id: Long,
    @ColumnInfo(name = AddressContract.Columns.ADDRESS)
    val address: String
)
