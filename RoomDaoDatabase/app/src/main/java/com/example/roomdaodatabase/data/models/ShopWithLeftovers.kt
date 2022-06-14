package com.example.roomdaodatabase.data.models

import androidx.room.Embedded
import androidx.room.Relation

data class ShopWithLeftovers(
    @Embedded
    val shop: Shop,
    @Relation(
        parentColumn = ShopContract.Columns.ID,
        entityColumn = LeftoversContract.Columns.SHOP_ID
    )
    val leftovers: List<Leftovers>
    //val listProductId: List<Long>
)