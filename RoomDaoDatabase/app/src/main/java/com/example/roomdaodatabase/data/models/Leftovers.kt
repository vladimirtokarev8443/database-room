package com.example.roomdaodatabase.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = LeftoversContract.TABLE_NAME)
data class Leftovers(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = LeftoversContract.Columns.ID)
    val id: Long,
    @ColumnInfo(name = LeftoversContract.Columns.SHOP_ID)
    val shopId: Long,
    @ColumnInfo(name = LeftoversContract.Columns.PRODUCT_ID)
    val productId: Long,
    @ColumnInfo(name = LeftoversContract.Columns.COUNT)
    val count: Int
)
