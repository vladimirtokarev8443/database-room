package com.example.roomdaodatabase.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.withTransaction
import com.example.roomdaodatabase.data.Database
import com.example.roomdaodatabase.data.SharedRepository
import com.example.roomdaodatabase.data.models.*
import com.example.roomdaodatabase.enum.OrderStatus
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SharedViewModel: ViewModel() {
    private val repository = SharedRepository()

    private val addressIdMutLiveData = MutableLiveData<Long>()
    val addressIdLiveData: LiveData<Long>
    get() = addressIdMutLiveData

    private val addressAndShopsMut = MutableLiveData<List<AddressAndShop>>()
    val addressAndShops: LiveData<List<AddressAndShop>>
    get() = addressAndShopsMut

    private val addressAndEmployeesMut = MutableLiveData<List<AddressAndEmployee>>()
    val addressAndEmployees: LiveData<List<AddressAndEmployee>>
    get() = addressAndEmployeesMut

    private val addressAndSuppliersMut = MutableLiveData<List<AddressAndSupplier>>()
    val addressAndSuppliers: LiveData<List<AddressAndSupplier>>
    get() = addressAndSuppliersMut

    private val suppliersWithProductsMut = MutableLiveData<List<ProductAndSupplier>>()
    val suppliersWithProductsAndSupplier: LiveData<List<ProductAndSupplier>>
    get() = suppliersWithProductsMut

    private val suppliersMutLiveData = MutableLiveData<List<Supplier>>()
    val supplierLiveData: LiveData<List<Supplier>>
    get() = suppliersMutLiveData

    private val productsMutLiveData = MutableLiveData<List<Product>>()
    val productsLiveData: LiveData<List<Product>>
    get() = productsMutLiveData

    private val leftoversMutableLiveData = MutableLiveData<List<ProductAndCount>>()
    val leftoversLiveData: LiveData<List<ProductAndCount>>
    get() = leftoversMutableLiveData

    private val ordersMutableLiveData = MutableLiveData<List<Order>>()
    val ordersLiveData: LiveData<List<Order>>
    get() = ordersMutableLiveData

    fun insertAddressAndGetAddressId(addressString: String){
        viewModelScope.launch {
            Database.instanse.withTransaction {
                val addressId = repository.insertAddress(addressString)
                addressIdMutLiveData.postValue(addressId)
            }
        }
    }

    fun insertShop(shop: Shop){
        viewModelScope.launch {
            repository.insertShop(shop)
        }
    }

    fun insertEmployee(employee: Employee){
        viewModelScope.launch {
            repository.insertEmployee(employee)
        }
    }

    fun insertSupplier(supplier: Supplier){
        viewModelScope.launch {
            repository.insertSupplier(supplier)
        }
    }

    fun insertProduct(product: Product){
        viewModelScope.launch {
            repository.insertProduct(product)
        }
    }

    fun insertLeftovers(leftovers: Leftovers){
        viewModelScope.launch {
            repository.insertLeftovers(leftovers)
        }
    }

    fun insertOrder(shopId: Long, supplierId: Long, productId: Long){
        viewModelScope.launch {
            Database.instanse.withTransaction {
                val titleShop = repository.getTitleShopById(shopId)
                val titleSupplier = repository.getTitleSupplierById(supplierId)
                val titleProduct = repository.getTitleProductById(productId)

                repository.insertOrder(
                    Order(
                        id = 0,
                        shopId = shopId,
                        titleShop = titleShop,
                        supplierId = supplierId,
                        titleSupplier = titleSupplier,
                        productId = productId,
                        titleProduct = titleProduct,
                        count = 10,
                        OrderStatus.NOTPAID.name
                        )
                )
            }
        }
    }

    fun removeEmployeeById(employeeId: Long){
        viewModelScope.launch {
            repository.removeEmployeById(employeeId)
        }
    }

    fun updateOrderStatus(status: String, orderId: Long) {
        viewModelScope.launch {
            repository.updateOrderStatus(status, orderId)
        }
    }

    fun updateCountLeftovers(orderId: Long){
        viewModelScope.launch {
                Database.instanse.withTransaction {
                    val order = repository.getOrderById(orderId)
                    val newCount = repository.getCountProductById(order.productId) + order.count
                    repository.updateLeftoversByProductId(newCount, order.productId)
                }

        }
    }

    fun getOrderBySupplierId(supplierId: Long){
        viewModelScope.launch {
            repository.getOrderBySupplierId(supplierId).collect {
                ordersMutableLiveData.postValue(it)
            }

        }
    }

    fun getOrderByShopId(shopId: Long){
        viewModelScope.launch {
            repository.getOrderByShopId(shopId).collect{
                ordersMutableLiveData.postValue(it)
            }
        }
    }

    fun getAllSuppliers(){
        viewModelScope.launch {
            suppliersMutLiveData.postValue(repository.getAllSuppliers())
        }
    }

    fun getAllProducts(){
        viewModelScope.launch {
            productsMutLiveData.postValue(repository.getAllProducts())
        }
    }

    fun getAddressAndShops(){
        viewModelScope.launch {
            val filterListByNull = repository.getAddressAndShop().filter {
                it.shop !=null
            }
            addressAndShopsMut.postValue(filterListByNull)

        }
    }

    fun getAddressAndEmployees(shopId: Long){
        viewModelScope.launch {
            val filtrListByShopId = repository.getAddresAndEmployee().filter {
                it.employee?.shopId == shopId
            }
            addressAndEmployeesMut.postValue(filtrListByShopId)
        }
    }

    fun getAddresAndSuppliers(){
        viewModelScope.launch {
           val filterListByNull = repository.getAddressAndSupplier().filter {
                it.supplier != null
            }
            addressAndSuppliersMut.postValue(filterListByNull)
        }
    }


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

    fun getLeftoversByShopId(shopId: Long){
        viewModelScope.launch {
            var productAndCounts = emptyList<ProductAndCount>()
            Log.d("testR", "${repository.getLeftoversByShopId(shopId)}")

            val shopWithLeftovers = repository.getLeftoversByShopId(shopId)
            shopWithLeftovers.leftovers.forEach { leftovers ->
                val product = repository.getProductById(leftovers.productId)

                productAndCounts = productAndCounts + listOf(ProductAndCount(product, leftovers))
            }
        leftoversMutableLiveData.postValue(productAndCounts)
        }
    }

}

