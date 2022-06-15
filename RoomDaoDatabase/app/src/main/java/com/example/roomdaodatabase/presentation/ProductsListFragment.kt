package com.example.roomdaodatabase.presentation

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomdaodatabase.R
import com.example.roomdaodatabase.adapter.ProductAndSupplierAdapter
import com.example.roomdaodatabase.databinding.FragmentProductsListBinding

class ProductsListFragment: Fragment(R.layout.fragment_products_list) {
    private var _binding: FragmentProductsListBinding? = null
    private val binding get() = _binding!!
    //private val viewModel: SharedViewModel by viewModels()
    private val viewModel: ProductsListViewModel by viewModels()
    private var productAndSupplierAdapter: ProductAndSupplierAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentProductsListBinding.bind(view)

        initList()
        bindViewModel()
        getSupplierWithProduct()

        binding.addProductButton.setOnClickListener {
            findNavController().navigate(R.id.action_productsListFragment_to_addProductFragment)
        }

    }

    private fun initList(){
        productAndSupplierAdapter = ProductAndSupplierAdapter()
        with(binding.productList){
            adapter = productAndSupplierAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
    }

    private fun bindViewModel(){
        viewModel.suppliersWithProductsAndSupplier.observe(viewLifecycleOwner){
            productAndSupplierAdapter?.items = it
        }
    }

    private fun getSupplierWithProduct(){
        viewModel.getProductAndSupplier()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        productAndSupplierAdapter = null
    }
}