package com.example.roomdaodatabase.data

import com.example.roomdaodatabase.data.models.*
import kotlinx.coroutines.flow.Flow

class SharedRepository {
    private val addressDao = Database.instanse.addressDao()
    private val employeeDao = Database.instanse.employeeDao()
    private val leftoversDao = Database.instanse.leftoversDao()
    private val orderDao = Database.instanse.orderDao()
    private val productDao = Database.instanse.productDao()
    private val shopDao = Database.instanse.shopDao()
    private val supplierDao = Database.instanse.supplierDao()


//Address
    suspend fun insertAddress(addressShop: String): Long{
        return addressDao.insertAdress(Address(0,addressShop))

    }

//Shop
    suspend fun insertShop(shop: Shop){
        shopDao.insertShop(shop)
    }

    suspend fun getAddressAndShop(): List<AddressAndShop>{
        return addressDao.getAddressAndShop()
    }

    suspend fun getTitleShopById(shopId: Long): String{
        return shopDao.getTitleShopById(shopId)
    }

//Employee
    suspend fun insertEmployee(employee: Employee){
        employeeDao.insertEmployee(employee)
    }

    suspend fun getAddresAndEmployee(): List<AddressAndEmployee>{
        return addressDao.getAddressAndEmployee()
    }

    suspend fun removeEmployeById(employeeId: Long){
        return employeeDao.removeEmployeeById(employeeId)
    }

//Supplier
    suspend fun insertSupplier(supplier: Supplier){
        supplierDao.insertSupplier(supplier)
    }

    suspend fun getAddressAndSupplier(): List<AddressAndSupplier>{
        return addressDao.getAddressAndSupplier()
    }

    suspend fun getAllSuppliers():List<Supplier>{
        return supplierDao.getAllSuppliers()
    }

    suspend fun getTitleSupplierById(supplierId: Long): String{
        return supplierDao.getTitleSupplierById(supplierId)
    }

//Product
    suspend fun insertProduct(product: Product){
        productDao.insertProduct(product)
    }

    suspend fun getAllProducts(): List<Product>{
        return productDao.getAllProducts()
    }

    suspend fun getProductById(productId: Long): Product{
        return productDao.getProductById(productId)
    }

    suspend fun getTitleProductById(productId: Long): String{
        return productDao.getTitleProductById(productId)
    }

//Leftovers
    suspend fun insertLeftovers(leftovers: Leftovers){
        leftoversDao.insertLeftovers(leftovers)
    }

    suspend fun getLeftoversByShopId(shopId: Long): ShopWithLeftovers{
        return leftoversDao.getLeftoversByShopId(shopId)
    }

    suspend fun getCountProductById(productId: Long): Int {
        return leftoversDao.getCountProductById(productId)
    }
    suspend fun updateLeftoversByProductId(count: Int, productId: Long){
        leftoversDao.updateLeftoversByProductId(count, productId)
    }

//Order
    suspend fun insertOrder(order: Order){
        orderDao.insertOrder(order)
    }

    suspend fun getOrderById(orderId: Long): Order{
        return orderDao.getOrderById(orderId)
    }

    fun getOrderBySupplierId(supplierId:Long): Flow<List<Order>> {
        return orderDao.getOrdersBySupplierId(supplierId)
    }

    fun getOrderByShopId(shopId:Long): Flow<List<Order>>{
        return orderDao.getOrdersByShopId(shopId)
    }

    suspend fun updateOrderStatus(status: String, orderId: Long){
        orderDao.updateOrderStatus(status, orderId)
    }
}

