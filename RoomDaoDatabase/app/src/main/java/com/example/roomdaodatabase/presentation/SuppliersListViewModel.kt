package com.example.roomdaodatabase.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomdaodatabase.data.SuppliersListRepository
import com.example.roomdaodatabase.data.models.AddressAndSupplier
import kotlinx.coroutines.launch

class SuppliersListViewModel: ViewModel() {
    private val repository = SuppliersListRepository()

    private val addressAndSuppliersMut = MutableLiveData<List<AddressAndSupplier>>()
    val addressAndSuppliers: LiveData<List<AddressAndSupplier>>
        get() = addressAndSuppliersMut

    fun getAddresAndSuppliers(){
        viewModelScope.launch {
            val filterListByNull = repository.getAddressAndSupplier().filter {
                it.supplier != null
            }
            addressAndSuppliersMut.postValue(filterListByNull)
        }
    }
}