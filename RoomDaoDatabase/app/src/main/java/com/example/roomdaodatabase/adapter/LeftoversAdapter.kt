package com.example.roomdaodatabase.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.roomdaodatabase.data.models.ProductAndCount
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class LeftoversAdapter(
    onProductIdAndSupplierId: (Long, Long) -> Unit
) : AsyncListDifferDelegationAdapter<ProductAndCount>(ProductAndCountAdapterDiffUtilCallback()) {

    init {
        delegatesManager.addDelegate(LeftoversAdapterDelegate(onProductIdAndSupplierId))
    }


    private class ProductAndCountAdapterDiffUtilCallback : DiffUtil.ItemCallback<ProductAndCount>() {
        override fun areItemsTheSame(
            oldItem: ProductAndCount,
            newItem: ProductAndCount
        ): Boolean {
            return oldItem.leftovers.id == newItem.leftovers.id
        }

        override fun areContentsTheSame(
            oldItem: ProductAndCount,
            newItem: ProductAndCount
        ): Boolean {
            return oldItem == newItem
        }

    }
}