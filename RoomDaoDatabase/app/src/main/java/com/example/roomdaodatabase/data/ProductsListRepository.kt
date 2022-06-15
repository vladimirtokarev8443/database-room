package com.example.roomdaodatabase.data

import com.example.roomdaodatabase.data.db.Database
import com.example.roomdaodatabase.data.models.Product
import com.example.roomdaodatabase.data.models.Supplier

class ProductsListRepository {

    suspend fun getAllProducts(): List<Product>{
        return Database.instanse.productDao().getAllProducts()
    }

    suspend fun getAllSuppliers():List<Supplier>{
        return Database.instanse.supplierDao().getAllSuppliers()
    }
}