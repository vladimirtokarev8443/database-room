package com.example.roomdaodatabase.data.models

import androidx.room.Embedded
import androidx.room.Relation

data class AddressAndEmployee(
    @Embedded
    val address: Address?,
    @Relation(
        parentColumn = AddressContract.Columns.ID,
        entityColumn = EmployeeContract.Columns.ADDRESS_ID
    )
    val employee: Employee?
)
