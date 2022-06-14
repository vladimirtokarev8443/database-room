package com.example.roomdaodatabase.presentation

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.roomdaodatabase.R
import com.example.roomdaodatabase.data.models.Leftovers
import com.example.roomdaodatabase.databinding.FragmentAddLeftoversProductBinding

class AddLeftoversProductFragment: Fragment(R.layout.fragment_add_leftovers_product), AdapterView.OnItemSelectedListener {
    private var _binding: FragmentAddLeftoversProductBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SharedViewModel by viewModels()
    private val args: AddLeftoversProductFragmentArgs by navArgs()
    private var productId: Long = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentAddLeftoversProductBinding.bind(view)

        getAllProduct()
        bindViewModel()

        binding.addProductInLeftoversButton.setOnClickListener {
            if(productId != 0L){
                viewModel.insertLeftovers(
                    Leftovers(
                        id = 0,
                        shopId = args.shopId,
                        productId = productId,
                        count = 0
                    )
                )
                findNavController().popBackStack()
            }
        }
    }

    private fun bindViewModel(){
        viewModel.productsLiveData.observe(viewLifecycleOwner){productList ->
            var titleProducts = listOf("Select product")

            productList.forEach { supplier ->
                titleProducts = titleProducts + listOf(supplier.title)
            }

            initSpinner(titleProducts.toTypedArray())
        }

    }
    private fun initSpinner(arrayTitleProduct: Array<String>){
        val adapterSpinner = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, arrayTitleProduct)
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        with(binding.spinner){
            adapter = adapterSpinner
            onItemSelectedListener = this@AddLeftoversProductFragment
        }
    }

    private fun getAllProduct(){
        viewModel.getAllProducts()
    }


    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        productId = p3
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}