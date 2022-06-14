package com.example.roomdaodatabase.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdaodatabase.data.models.ProductAndCount
import com.example.roomdaodatabase.databinding.ItemLeftoversBinding
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate

class LeftoversAdapterDelegate(
    val onProductIdAndSupplierId: (Long, Long) -> Unit
): AbsListItemAdapterDelegate<ProductAndCount, ProductAndCount, LeftoversAdapterDelegate.ProductAndCountHolder>() {

    override fun isForViewType(
        item: ProductAndCount,
        items: MutableList<ProductAndCount>,
        position: Int
    ): Boolean {
        return true
    }

    override fun onCreateViewHolder(parent: ViewGroup): ProductAndCountHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = ItemLeftoversBinding.inflate(inflater, parent, false)
        return ProductAndCountHolder(view, onProductIdAndSupplierId)
    }

    override fun onBindViewHolder(
        item: ProductAndCount,
        holder: ProductAndCountHolder,
        payloads: MutableList<Any>
    ) {
        holder.bind(item)
    }

    class ProductAndCountHolder(
        val binding: ItemLeftoversBinding,
        val onProductIdAndSupplierId: (Long, Long) -> Unit
    ): RecyclerView.ViewHolder(binding.root){

        fun bind(productAndCount: ProductAndCount){
            val productId = productAndCount.product.id
            val supplierId = productAndCount.product.supplierId
            binding.productTextView.text = productAndCount.product.title
            binding.countProductTextView.text = productAndCount.leftovers.count.toString()

            binding.addToOrderButton.setOnClickListener {
                    onProductIdAndSupplierId(productId, supplierId)
            }
        }
    }
}