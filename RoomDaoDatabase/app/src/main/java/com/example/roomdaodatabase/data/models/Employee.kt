package com.example.roomdaodatabase.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.math.BigDecimal
import java.sql.Date

@Entity(tableName = EmployeeContract.TABLE_NAME)
data class Employee(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = EmployeeContract.Columns.ID)
    val id: Long,
    @ColumnInfo(name = EmployeeContract.Columns.SHOP_ID)
    val shopId: Long,
    @ColumnInfo(name = EmployeeContract.Columns.NAME)
    val name: String,
    @ColumnInfo(name = EmployeeContract.Columns.ADDRESS_ID)
    val addressId: Long,
    @ColumnInfo(name = EmployeeContract.Columns.AGE, defaultValue = "0")
    val age: Int
)
