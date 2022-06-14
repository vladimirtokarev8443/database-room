package com.example.roomdaodatabase.presentation

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomdaodatabase.R
import com.example.roomdaodatabase.adapter.AddressAndShopAdapter
import com.example.roomdaodatabase.databinding.FragmentShopsListBinding
import com.example.roomdaodatabase.enum.TypeEntity

class ShopsListFragment: Fragment(R.layout.fragment_shops_list) {
    private var _binding: FragmentShopsListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SharedViewModel by viewModels()
    private var addressAndShopAdapter: AddressAndShopAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentShopsListBinding.bind(view)

        initList()
        bindViewModel()
        getAddressAndShops()

        binding.addShopButton.setOnClickListener {
            findNavController().navigate(R.id.action_shopsListFragment_to_addShopFragment)
        }
    }

    private fun initList(){
        addressAndShopAdapter = AddressAndShopAdapter{shopId, typeEntity ->
            when(TypeEntity.valueOf(typeEntity)){
                TypeEntity.EMPLOYEE -> {
                    findNavController().navigate(ShopsListFragmentDirections
                        .actionShopsListFragmentToEmployeesListFragment(shopId)
                    )
                }
                TypeEntity.PRODUCT -> {
                    findNavController().navigate(ShopsListFragmentDirections
                        .actionShopsListFragmentToLeftoversListFragment(shopId)
                    )
                }
                TypeEntity.ORDER -> {
                    findNavController().navigate(ShopsListFragmentDirections
                        .actionShopsListFragmentToOrdersListFragment(shopId, TypeEntity.SHOP.name))
                }

            }

        }
        with(binding.shopsList){
            adapter = addressAndShopAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
    }

    private fun bindViewModel(){
        viewModel.addressAndShops.observe(viewLifecycleOwner){
            Log.d("testR", "${it}")
            addressAndShopAdapter?.items = it
        }
    }

   private fun getAddressAndShops(){
       viewModel.getAddressAndShops()
   }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        addressAndShopAdapter = null
    }
}