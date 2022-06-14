package com.example.roomdaodatabase.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.roomdaodatabase.data.models.AddressAndShop
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class AddressAndShopAdapter(
    onId: (Long, String) -> Unit
) : AsyncListDifferDelegationAdapter<AddressAndShop>(AddressAndShopAdapterDiffUtilCallback()) {

    init {
        delegatesManager.addDelegate(AddressAndShopAdapterDelegate(onId))
    }


    private class AddressAndShopAdapterDiffUtilCallback : DiffUtil.ItemCallback<AddressAndShop>() {
        override fun areItemsTheSame(
            oldItem: AddressAndShop,
            newItem: AddressAndShop
        ): Boolean {
            return oldItem.shop?.id == newItem.shop?.id
        }

        override fun areContentsTheSame(
            oldItem: AddressAndShop,
            newItem: AddressAndShop
        ): Boolean {
            return oldItem == newItem
        }

    }

}