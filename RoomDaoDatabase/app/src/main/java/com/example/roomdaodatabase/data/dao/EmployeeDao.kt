package com.example.roomdaodatabase.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.roomdaodatabase.data.models.Employee
import com.example.roomdaodatabase.data.models.EmployeeContract

@Dao
interface EmployeeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEmployee(employee: Employee)

    @Query("DELETE FROM ${EmployeeContract.TABLE_NAME} WHERE ${EmployeeContract.Columns.ID}=:employeeId")
    suspend fun removeEmployeeById(employeeId: Long)
}