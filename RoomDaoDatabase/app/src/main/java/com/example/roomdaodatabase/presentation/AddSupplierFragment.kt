package com.example.roomdaodatabase.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.roomdaodatabase.R
import com.example.roomdaodatabase.data.models.Supplier
import com.example.roomdaodatabase.databinding.FragmentAddSupplierBinding

class AddSupplierFragment: Fragment(R.layout.fragment_add_supplier) {
    private var _binding: FragmentAddSupplierBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SharedViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentAddSupplierBinding.bind(view)

        bindViewModel()

        binding.addSupplierbutton.setOnClickListener {
            val addressSupplier = binding.addressSupplierEditText.text.toString()

            if (addressSupplier.isNotBlank()){
                viewModel.insertAddressAndGetAddressId(addressSupplier)
            }
        }
    }

    private fun bindViewModel(){
        viewModel.addressIdLiveData.observe(viewLifecycleOwner){addressId ->
            val titleSupplier = binding.titleSupplierEditText.text.toString()
            if (titleSupplier.isNotBlank()){
                viewModel.insertSupplier(
                    Supplier(
                        id = 0,
                        title = titleSupplier,
                        addressId = addressId
                    )
                )
                findNavController().popBackStack()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}