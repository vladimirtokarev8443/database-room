package com.example.roomdaodatabase.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.roomdaodatabase.data.models.Shop
import com.example.roomdaodatabase.data.models.ShopContract


@Dao
interface ShopDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertShop(shop: Shop)

    @Query("SELECT ${ShopContract.Columns.TITLE} FROM ${ShopContract.TABLE_NAME} WHERE ${ShopContract.Columns.ID}=:shopId")
    suspend fun getTitleShopById(shopId:Long): String
}