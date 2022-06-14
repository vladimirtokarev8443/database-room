package com.example.roomdaodatabase.data.models

import androidx.room.Embedded
import androidx.room.Relation

data class ProductAndSupplier(
    val supplierTitle: String,
    val product: Product
)
