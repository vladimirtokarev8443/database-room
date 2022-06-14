package com.example.roomdaodatabase.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdaodatabase.data.models.ProductAndSupplier
import com.example.roomdaodatabase.databinding.ItemProductBinding
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate

class ProductAndSupplierAdapterDelegate(
): AbsListItemAdapterDelegate<ProductAndSupplier, ProductAndSupplier, ProductAndSupplierAdapterDelegate.SupplierWithProductHolder>() {

    override fun isForViewType(
        item: ProductAndSupplier,
        items: MutableList<ProductAndSupplier>,
        position: Int
    ): Boolean {
        return true
    }

    override fun onCreateViewHolder(parent: ViewGroup): SupplierWithProductHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = ItemProductBinding.inflate(inflater, parent, false)
        return SupplierWithProductHolder(view)
    }

    override fun onBindViewHolder(
        item: ProductAndSupplier,
        holder: SupplierWithProductHolder,
        payloads: MutableList<Any>
    ) {
        holder.bind(item)
    }

    class SupplierWithProductHolder(
        val binding: ItemProductBinding
    ): RecyclerView.ViewHolder(binding.root){

        fun bind(productAndSupplier: ProductAndSupplier){
            binding.titleTextView.text = productAndSupplier.product.title
            binding.titleSupplierTextView.text = productAndSupplier.supplierTitle

        }
    }
}