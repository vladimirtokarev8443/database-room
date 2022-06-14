package com.example.roomdaodatabase.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.roomdaodatabase.R
import com.example.roomdaodatabase.data.models.Shop
import com.example.roomdaodatabase.databinding.FragmentAddShopBinding

class AddShopFragment: Fragment(R.layout.fragment_add_shop) {
    private var _binding: FragmentAddShopBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SharedViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentAddShopBinding.bind(view)

        bindViewModel()

        binding.addShopButton.setOnClickListener {
            val addressShop = binding.addressShopEditText.text.toString()
            if (addressShop.isNotBlank()){
                viewModel.insertAddressAndGetAddressId(addressShop)
            }
        }
    }

    private fun bindViewModel(){
        viewModel.addressIdLiveData.observe(viewLifecycleOwner){ addressId ->
            val titleShop = binding.titleShopEditText.text.toString()

            if (titleShop.isNotBlank()) {
                viewModel.insertShop(
                    Shop(
                        id = 0,
                        title = titleShop,
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