package com.example.roomdaodatabase.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdaodatabase.data.models.AddressAndSupplier
import com.example.roomdaodatabase.databinding.ItemShopBinding
import com.example.roomdaodatabase.databinding.ItemSupplierBinding
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate

class AddressAndSupplierAdapterDelegate(
    val onId: (Long) -> Unit
): AbsListItemAdapterDelegate<AddressAndSupplier, AddressAndSupplier, AddressAndSupplierAdapterDelegate.AddressAndSupplierHolder>() {

    override fun isForViewType(
        item: AddressAndSupplier,
        items: MutableList<AddressAndSupplier>,
        position: Int
    ): Boolean {
        return true
    }

    override fun onCreateViewHolder(parent: ViewGroup): AddressAndSupplierHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = ItemSupplierBinding.inflate(inflater, parent, false)
        return AddressAndSupplierHolder(view, onId)
    }

    override fun onBindViewHolder(
        item: AddressAndSupplier,
        holder: AddressAndSupplierHolder,
        payloads: MutableList<Any>
    ) {
        holder.bind(item)
    }

    class AddressAndSupplierHolder(
        val binding: ItemSupplierBinding,
        val onId: (Long) -> Unit
    ): RecyclerView.ViewHolder(binding.root){


        fun bind(addressAndSupplier: AddressAndSupplier){
            binding.titleTextView.text = addressAndSupplier.supplier?.title
            binding.addressTextView.text = addressAndSupplier.address.address
            binding.ordersButton.setOnClickListener {
                addressAndSupplier.supplier?.id?.let {id -> onId(id) }
            }

        }
    }
}