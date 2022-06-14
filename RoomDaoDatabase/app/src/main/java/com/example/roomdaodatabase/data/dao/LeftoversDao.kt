package com.example.roomdaodatabase.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.roomdaodatabase.data.models.*

@Dao
interface LeftoversDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLeftovers(leftovers:Leftovers)

    @Query("SELECT * FROM ${ShopContract.TABLE_NAME} WHERE ${ShopContract.Columns.ID}=:shopId")
    suspend fun getLeftoversByShopId(shopId: Long): ShopWithLeftovers

    @Query("SELECT ${LeftoversContract.Columns.COUNT} FROM ${LeftoversContract.TABLE_NAME} WHERE ${LeftoversContract.Columns.PRODUCT_ID}=:productId")
    suspend fun getCountProductById(productId: Long): Int

    @Query("UPDATE ${LeftoversContract.TABLE_NAME} SET ${LeftoversContract.Columns.COUNT}=:count WHERE ${LeftoversContract.Columns.PRODUCT_ID}=:productId")
    suspend fun updateLeftoversByProductId(count: Int, productId: Long)
}