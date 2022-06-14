package com.example.roomdaodatabase.data.models

object OrderContract {
    const val TABLE_NAME = "orders"

    object Columns {
        const val ID = "id"
        const val SHOP_ID = "shop_id"
        const val TITLE_SHOP = "title_shop"
        const val SUPPLIER_ID = "supplier_id"
        const val TITLE_SUPPLIER = "title_supplier"
        const val PRODUCT_ID = "product_id"
        const val TITLE_PRODUCT = "title_product"
        const val COUNT = "count"
        const val STATUS = "status"
    }
}