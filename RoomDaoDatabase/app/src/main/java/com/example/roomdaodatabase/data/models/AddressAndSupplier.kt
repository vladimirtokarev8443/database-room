package com.example.roomdaodatabase.data.models

import androidx.room.Embedded
import androidx.room.Relation

data class AddressAndSupplier(
    @Embedded
    val address: Address,
    @Relation(
        parentColumn = AddressContract.Columns.ID,
        entityColumn = SupplierContract.Columns.ADDRESS_ID
    )
    val supplier: Supplier?
)
