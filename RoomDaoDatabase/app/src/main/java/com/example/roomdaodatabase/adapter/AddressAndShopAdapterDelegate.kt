package com.example.roomdaodatabase.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdaodatabase.data.models.Address
import com.example.roomdaodatabase.data.models.AddressAndShop
import com.example.roomdaodatabase.data.models.Shop
import com.example.roomdaodatabase.databinding.ItemShopBinding
import com.example.roomdaodatabase.enum.TypeEntity
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate

class AddressAndShopAdapterDelegate(
    val onId: (Long, String) -> Unit
): AbsListItemAdapterDelegate<AddressAndShop, AddressAndShop, AddressAndShopAdapterDelegate.AddressAndShopHolder>() {

    override fun isForViewType(
        item: AddressAndShop,
        items: MutableList<AddressAndShop>,
        position: Int
    ): Boolean {
        return true
    }

    override fun onCreateViewHolder(parent: ViewGroup): AddressAndShopHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = ItemShopBinding.inflate(inflater, parent, false)
        return AddressAndShopHolder(view, onId)
    }

    override fun onBindViewHolder(
        item: AddressAndShop,
        holder: AddressAndShopHolder,
        payloads: MutableList<Any>
    ) {
        holder.bind(item)
    }

    class AddressAndShopHolder(
        val binding: ItemShopBinding,
        onId: (Long, String) -> Unit
    ): RecyclerView.ViewHolder(binding.root){
        var shopId: Long? = null
        init {
            binding.employeesButton.setOnClickListener {
                shopId?.let{ onId(it, TypeEntity.EMPLOYEE.name) }
            }
            binding.leftoversButton.setOnClickListener {
                shopId?.let { onId(it, TypeEntity.PRODUCT.name) }
            }

            binding.ordersButton.setOnClickListener {
                shopId?.let { onId(it, TypeEntity.ORDER.name) }
            }
        }

        fun bind(addressAndShop: AddressAndShop){
            shopId = addressAndShop.shop?.id
            binding.titleTextView.text = addressAndShop.shop?.title
            binding.addressTextView.text = addressAndShop.address?.address


        }
    }
}