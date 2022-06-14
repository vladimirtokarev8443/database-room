package com.example.roomdaodatabase.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = OrderContract.TABLE_NAME
)

data class Order(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = OrderContract.Columns.ID)
    val id: Long,
    @ColumnInfo(name = OrderContract.Columns.SHOP_ID)
    val shopId: Long,
    @ColumnInfo(name = OrderContract.Columns.TITLE_SHOP)
    val titleShop: String,
    @ColumnInfo(name = OrderContract.Columns.SUPPLIER_ID)
    val supplierId: Long,
    @ColumnInfo(name = OrderContract.Columns.TITLE_SUPPLIER)
    val titleSupplier: String,
    @ColumnInfo(name = OrderContract.Columns.PRODUCT_ID)
    val productId: Long,
    @ColumnInfo(name = OrderContract.Columns.TITLE_PRODUCT)
    val titleProduct: String,
    @ColumnInfo(name = OrderContract.Columns.COUNT)
    val count: Int,
    @ColumnInfo(name = OrderContract.Columns.STATUS)
    val status: String
)
