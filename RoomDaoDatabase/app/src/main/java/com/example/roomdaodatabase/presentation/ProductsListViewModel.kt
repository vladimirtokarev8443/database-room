package com.example.roomdaodatabase.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomdaodatabase.data.ProductsListRepository
import com.example.roomdaodatabase.data.models.ProductAndSupplier
import kotlinx.coroutines.launch

class ProductsListViewModel: ViewModel() {

    private val repository = ProductsListRepository()

    private val suppliersWithProductsMut = MutableLiveData<List<ProductAndSupplier>>()
    val suppliersWithProductsAndSupplier: LiveData<List<ProductAndSupplier>>
        get() = suppliersWithProductsMut

    fun getProductAndSupplier(){
        viewModelScope.launch {
            var productAndSuppliers = emptyList<ProductAndSupplier>()
            val products = repository.getAllProducts()
            val suppliers = repository.getAllSuppliers()

            products.forEach{product ->
                suppliers.forEach { supplier ->
                    if (supplier.id == product.supplierId){
                        productAndSuppliers = productAndSuppliers + listOf(ProductAndSupplier(supplier.title, product))
                    }
                }
            }

            suppliersWithProductsMut.postValue(productAndSuppliers)
        }
    }
}