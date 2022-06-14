package com.example.roomdaodatabase.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomdaodatabase.R
import com.example.roomdaodatabase.adapter.AddressAndSupplierAdapter
import com.example.roomdaodatabase.databinding.FragmentSuppliersListBinding
import com.example.roomdaodatabase.enum.TypeEntity

class SuppliersListFragment: Fragment(R.layout.fragment_suppliers_list) {
    private var _binding: FragmentSuppliersListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SharedViewModel by viewModels()
    private var addressAndSupplierAdapter: AddressAndSupplierAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSuppliersListBinding.bind(view)

        initlist()
        bindViewModel()
        getAddressAndSuppliers()

        binding.addSupplierButton.setOnClickListener {
            findNavController().navigate(R.id.action_suppliersListFragment_to_addSupplierFragment)
        }
    }

    private fun initlist(){
        addressAndSupplierAdapter = AddressAndSupplierAdapter { supplierId ->
            findNavController().navigate(SuppliersListFragmentDirections
                .actionSuppliersListFragmentToOrdersListFragment(supplierId, TypeEntity.SUPPLIER.name))
        }
        with(binding.suppliersList){
            adapter = addressAndSupplierAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
    }

    private fun bindViewModel(){
        viewModel.addressAndSuppliers.observe(viewLifecycleOwner){
            addressAndSupplierAdapter?.items = it
        }
    }

    private fun getAddressAndSuppliers(){
        viewModel.getAddresAndSuppliers()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        addressAndSupplierAdapter = null
    }
}