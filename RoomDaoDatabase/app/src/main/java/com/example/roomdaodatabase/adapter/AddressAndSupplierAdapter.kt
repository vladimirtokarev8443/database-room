package com.example.roomdaodatabase.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.roomdaodatabase.data.models.AddressAndSupplier
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class AddressAndSupplierAdapter(
    onId: (Long) -> Unit
) : AsyncListDifferDelegationAdapter<AddressAndSupplier>(AddressAndSupplierAdapterDiffUtilCallback()) {

    init {
        delegatesManager.addDelegate(AddressAndSupplierAdapterDelegate(onId))
    }


    private class AddressAndSupplierAdapterDiffUtilCallback : DiffUtil.ItemCallback<AddressAndSupplier>() {
        override fun areItemsTheSame(
            oldItem: AddressAndSupplier,
            newItem: AddressAndSupplier
        ): Boolean {
            return oldItem.supplier?.id == newItem.supplier?.id
        }

        override fun areContentsTheSame(
            oldItem: AddressAndSupplier,
            newItem: AddressAndSupplier
        ): Boolean {
            return oldItem == newItem
        }

    }

}