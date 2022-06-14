package com.example.roomdaodatabase.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.roomdaodatabase.Converters
import com.example.roomdaodatabase.data.dao.*
import com.example.roomdaodatabase.data.models.*

@Database(
    entities = [
        Address::class,
        Employee::class,
        Leftovers::class,
        Order::class,
        Product::class,
        Shop::class,
        Supplier::class,
    ],
    version = StoreChainDatabase.DB_VERSION
)
@TypeConverters(Converters::class)
abstract class StoreChainDatabase: RoomDatabase() {

    abstract fun addressDao(): AddressDao
    abstract fun employeeDao(): EmployeeDao
    abstract fun leftoversDao(): LeftoversDao
    abstract fun orderDao(): OrderDao
    abstract fun productDao(): ProductDao
    abstract fun shopDao(): ShopDao
    abstract fun supplierDao(): SupplierDao

    companion object {
        const val DB_VERSION = 2
        const val DB_NAME = "StoreChainDatabase"
    }
}