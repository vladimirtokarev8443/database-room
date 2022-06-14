package com.example.roomdaodatabase.data.models

import androidx.room.Embedded
import androidx.room.Relation

data class AddressAndShop(
    @Embedded
    val address: Address?,
    @Relation(
        parentColumn = AddressContract.Columns.ID,
        entityColumn = ShopContract.Columns.ADDRESS_ID
    )
    val shop: Shop?
)