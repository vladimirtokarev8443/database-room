package com.example.roomdaodatabase.data.dao

import androidx.room.*
import com.example.roomdaodatabase.data.models.Order
import com.example.roomdaodatabase.data.models.OrderContract
import kotlinx.coroutines.flow.Flow

@Dao
interface OrderDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrder(order: Order)

    @Query("SELECT * FROM ${OrderContract.TABLE_NAME} WHERE ${OrderContract.Columns.SUPPLIER_ID}=:supplierId")
    fun getOrdersBySupplierId(supplierId: Long): Flow<List<Order>>

    @Query("SELECT * FROM ${OrderContract.TABLE_NAME} WHERE ${OrderContract.Columns.SHOP_ID}=:shopId")
    fun getOrdersByShopId(shopId: Long): Flow<List<Order>>

    @Query("SELECT * FROM ${OrderContract.TABLE_NAME} WHERE ${OrderContract.Columns.ID}=:orderId")
    suspend fun getOrderById(orderId: Long): Order

    @Query("UPDATE ${OrderContract.TABLE_NAME} SET ${OrderContract.Columns.STATUS}=:status WHERE ${OrderContract.Columns.ID}=:orderId")
    suspend fun updateOrderStatus(status: String, orderId: Long)


}