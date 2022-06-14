package com.example.roomdaodatabase.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.roomdaodatabase.data.models.Product
import com.example.roomdaodatabase.data.models.ProductContract

@Dao
interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(product: Product)

    @Query("SELECT * FROM ${ProductContract.TABLE_NAME}")
    suspend fun getAllProducts(): List<Product>

    @Query("SELECT ${ProductContract.Columns.TITLE} FROM ${ProductContract.TABLE_NAME} WHERE ${ProductContract.Columns.ID}=:productId")
    suspend fun getTitleProductById(productId: Long): String

    @Query("SELECT * FROM ${ProductContract.TABLE_NAME} WHERE ${ProductContract.Columns.ID}=:productId")
    suspend fun getProductById(productId: Long): Product
}