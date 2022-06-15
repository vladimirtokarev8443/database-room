package com.example.roomdaodatabase.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomdaodatabase.data.ShopsListRepository
import com.example.roomdaodatabase.data.models.AddressAndShop
import kotlinx.coroutines.launch

class ShopsListViewModel: ViewModel() {
    private val repository = ShopsListRepository()

    private val addressAndShopsMut = MutableLiveData<List<AddressAndShop>>()
    val addressAndShops: LiveData<List<AddressAndShop>>
        get() = addressAndShopsMut

    fun getAddressAndShops(){
        viewModelScope.launch {

            val filterListByNull = repository.getAddressAndShop().filter {
                it.shop !=null
            }
            addressAndShopsMut.postValue(filterListByNull)

        }
    }
}