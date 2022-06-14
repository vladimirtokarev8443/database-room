package com.example.roomdaodatabase.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.roomdaodatabase.data.models.ProductAndSupplier
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class ProductAndSupplierAdapter(
) : AsyncListDifferDelegationAdapter<ProductAndSupplier>(SupplierWithProductAdapterDiffUtilCallback()) {

    init {
        delegatesManager.addDelegate(ProductAndSupplierAdapterDelegate())
    }


    private class SupplierWithProductAdapterDiffUtilCallback : DiffUtil.ItemCallback<ProductAndSupplier>() {
        override fun areItemsTheSame(
            oldItem: ProductAndSupplier,
            newItem: ProductAndSupplier
        ): Boolean {
            return oldItem.product.id == newItem.product.id
        }

        override fun areContentsTheSame(
            oldItem: ProductAndSupplier,
            newItem: ProductAndSupplier
        ): Boolean {
            return oldItem == newItem
        }

    }
}