package com.example.roomdaodatabase.data.models

object ProductContract {
    const val TABLE_NAME = "products"

    object Columns {
        const val ID = "id"
        const val SUPPLIER_ID = "supplier_id"
        const val TITLE = "title"
        const val PRICE = "price"
    }
}