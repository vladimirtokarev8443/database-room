package com.example.roomdaodatabase.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdaodatabase.data.models.AddressAndEmployee
import com.example.roomdaodatabase.databinding.ItemEmployeeBinding
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate

class AddressAndEmployeeAdapterDelegate(
    val onId: (Long) -> Unit
): AbsListItemAdapterDelegate<AddressAndEmployee, AddressAndEmployee, AddressAndEmployeeAdapterDelegate.AddressAndEmployeeHolder>() {

    override fun isForViewType(
        item: AddressAndEmployee,
        items: MutableList<AddressAndEmployee>,
        position: Int
    ): Boolean {
        return true
    }

    override fun onCreateViewHolder(parent: ViewGroup): AddressAndEmployeeHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = ItemEmployeeBinding.inflate(inflater, parent, false)
        return AddressAndEmployeeHolder(view, onId)
    }

    override fun onBindViewHolder(
        item: AddressAndEmployee,
        holder: AddressAndEmployeeHolder,
        payloads: MutableList<Any>
    ) {
        holder.bind(item)
    }

    class AddressAndEmployeeHolder(
        val binding: ItemEmployeeBinding,
        val onId: (Long) -> Unit
    ): RecyclerView.ViewHolder(binding.root){


        fun bind(addressAndEmployee: AddressAndEmployee){
            binding.nameTextView.text = addressAndEmployee.employee?.name
            binding.addressTextView.text = addressAndEmployee.address?.address
            binding.ageTextView.text = addressAndEmployee.employee?.age.toString()

            binding.deleteEmployeeButton.setOnClickListener {
                addressAndEmployee.employee?.id?.let { id -> onId(id) }
            }
        }
    }
}