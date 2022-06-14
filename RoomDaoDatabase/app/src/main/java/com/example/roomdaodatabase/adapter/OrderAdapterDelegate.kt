package com.example.roomdaodatabase.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdaodatabase.R
import com.example.roomdaodatabase.data.models.Order
import com.example.roomdaodatabase.data.models.ProductAndCount
import com.example.roomdaodatabase.databinding.ItemLeftoversBinding
import com.example.roomdaodatabase.databinding.ItemOrderBinding
import com.example.roomdaodatabase.enum.OrderStatus
import com.example.roomdaodatabase.enum.TypeEntity
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate

class OrderAdapterDelegate (
    val type: String,
    val onOrderId: (Long) -> Unit
): AbsListItemAdapterDelegate<Order, Order, OrderAdapterDelegate.OrderHolder>() {

    override fun isForViewType(
        item: Order,
        items: MutableList<Order>,
        position: Int
    ): Boolean {
        return true
    }

    override fun onCreateViewHolder(parent: ViewGroup): OrderHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = ItemOrderBinding.inflate(inflater, parent, false)
        return OrderHolder(view, type, onOrderId)
    }

    override fun onBindViewHolder(
        item: Order,
        holder: OrderHolder,
        payloads: MutableList<Any>
    ) {
        holder.bind(item)
    }

    class OrderHolder(
        val binding: ItemOrderBinding,
        val type: String,
        val onOrderId: (Long) -> Unit
    ): RecyclerView.ViewHolder(binding.root){


        fun bind(order: Order){
            binding.titleProductTextView.text = order.titleProduct
            binding.countTextView.text = order.count.toString()
            binding.statusTextView.text = itemView.context.getString(R.string.status_order, order.status)
            binding.isStatusButton.setOnClickListener {
                onOrderId(order.id)
                it.isEnabled = false
                when(TypeEntity.valueOf(type)){
                    TypeEntity.SUPPLIER -> {
                        binding.statusTextView.text =
                            itemView.context.getString(R.string.status_order, OrderStatus.DELIVERED)
                    }
                    TypeEntity.SHOP -> {
                        binding.statusTextView.text =
                            itemView.context.getString(R.string.status_order, OrderStatus.PAID)
                    }
                }
            }

            when(TypeEntity.valueOf(type)){
                TypeEntity.SUPPLIER -> {
                    binding.titleTextView.text = order.titleShop
                    binding.isStatusButton.setText("Продать")
                    if (order.status == OrderStatus.PAID.name){
                        binding.isStatusButton.isEnabled = true
                    } else {
                        binding.isStatusButton.isEnabled = false
                    }
                }
                TypeEntity.SHOP -> {
                    binding.titleTextView.text = order.titleSupplier
                    binding.isStatusButton.setText("Оплатить")
                    if (order.status == OrderStatus.NOTPAID.name){
                        binding.isStatusButton.isEnabled = true
                    } else {
                        binding.isStatusButton.isEnabled = false
                    }
                }
            }
        }
    }
}