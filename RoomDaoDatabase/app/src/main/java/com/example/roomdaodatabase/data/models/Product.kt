package com.example.roomdaodatabase.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.math.BigDecimal

@Entity(tableName = ProductContract.TABLE_NAME)
data class Product(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ProductContract.Columns.ID)
    val id: Long,
    @ColumnInfo(name = ProductContract.Columns.SUPPLIER_ID)
    val supplierId: Long,
    @ColumnInfo(name = ProductContract.Columns.TITLE)
    val title: String,
    @ColumnInfo(name = ProductContract.Columns.PRICE)
    val date: BigDecimal = BigDecimal(123442337L)
)
