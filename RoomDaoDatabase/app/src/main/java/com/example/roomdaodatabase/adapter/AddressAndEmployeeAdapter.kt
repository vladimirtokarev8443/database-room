package com.example.roomdaodatabase.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.roomdaodatabase.data.models.AddressAndEmployee
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class AddressAndEmployeeAdapter(
    onId: (Long) -> Unit
) : AsyncListDifferDelegationAdapter<AddressAndEmployee>(AddressAndEmployeeAdapterDiffUtilCallback()) {

    init {
        delegatesManager.addDelegate(AddressAndEmployeeAdapterDelegate(onId))
    }


    private class AddressAndEmployeeAdapterDiffUtilCallback : DiffUtil.ItemCallback<AddressAndEmployee>() {
        override fun areItemsTheSame(
            oldItem: AddressAndEmployee,
            newItem: AddressAndEmployee
        ): Boolean {
            return oldItem.employee?.id == newItem.employee?.id
        }

        override fun areContentsTheSame(
            oldItem: AddressAndEmployee,
            newItem: AddressAndEmployee
        ): Boolean {
            return oldItem == newItem
        }

    }
}