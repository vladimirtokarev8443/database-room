package com.example.roomdaodatabase.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.roomdaodatabase.data.models.Order
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class OrderAdapter(
    type: String,
    onOrderId: (Long) -> Unit
) : AsyncListDifferDelegationAdapter<Order>(OrderAdapterDiffUtilCallback()) {

    init {
        delegatesManager.addDelegate(OrderAdapterDelegate(type, onOrderId))
    }


    private class OrderAdapterDiffUtilCallback : DiffUtil.ItemCallback<Order>() {
        override fun areItemsTheSame(
            oldItem: Order,
            newItem: Order
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: Order,
            newItem: Order
        ): Boolean {
            return oldItem == newItem
        }

    }
}