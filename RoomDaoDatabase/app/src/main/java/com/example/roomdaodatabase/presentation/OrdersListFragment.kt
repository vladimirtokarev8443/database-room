package com.example.roomdaodatabase.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomdaodatabase.R
import com.example.roomdaodatabase.adapter.OrderAdapter
import com.example.roomdaodatabase.databinding.FragmentOrdersListBinding
import com.example.roomdaodatabase.enum.OrderStatus
import com.example.roomdaodatabase.enum.TypeEntity

class OrdersListFragment: Fragment(R.layout.fragment_orders_list) {
    private var _binding: FragmentOrdersListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SharedViewModel by viewModels()
    private val args: OrdersListFragmentArgs by navArgs()
    private var orderAdapter: OrderAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding= FragmentOrdersListBinding.bind(view)

        initList()
        bindViewModel()
        getOrdersBySupplierIdOrShopId()
    }

    private fun initList(){
        orderAdapter = OrderAdapter(args.type){orderId ->
            when(TypeEntity.valueOf(args.type)){
                TypeEntity.SHOP -> {
                    viewModel.updateOrderStatus(OrderStatus.PAID.name, orderId)

                }
                TypeEntity.SUPPLIER -> {
                    viewModel.updateOrderStatus(OrderStatus.DELIVERED.name, orderId)
                    viewModel.updateCountLeftovers(orderId)
                }
            }
            //getOrdersBySupplierIdOrShopId()
        }
        with(binding.ordersList){
            adapter = orderAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
    }

    private fun bindViewModel(){
        viewModel.ordersLiveData.observe(viewLifecycleOwner){
            orderAdapter?.items = it
        }
    }

    private fun getOrdersBySupplierIdOrShopId(){
        when(TypeEntity.valueOf(args.type)){
            TypeEntity.SHOP ->viewModel.getOrderByShopId(args.id)
            TypeEntity.SUPPLIER ->viewModel.getOrderBySupplierId(args.id)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        orderAdapter = null
    }
}