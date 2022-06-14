package com.example.roomdaodatabase.presentation

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.roomdaodatabase.R
import com.example.roomdaodatabase.data.models.Product
import com.example.roomdaodatabase.databinding.FragmentAddProductBinding

class AddProductFragment: Fragment(R.layout.fragment_add_product), AdapterView.OnItemSelectedListener {
    private var _binding: FragmentAddProductBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SharedViewModel by viewModels()
    private var supplierId: Long =0


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentAddProductBinding.bind(view)

        getAllSuppliers()
        bindViewModel()

        binding.addProductButton.setOnClickListener {
            val titleProduct = binding.titleProductEditText.text.toString()

            if (titleProduct.isNotBlank() && supplierId != 0L) {
                viewModel.insertProduct(Product(0, supplierId, titleProduct))
                findNavController().popBackStack()
            }
        }
    }

    private fun initSpinner(arrayTitleSupplier: Array<String>){

        val adapterSpinner = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, arrayTitleSupplier)
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        with(binding.spinner){
            adapter = adapterSpinner
            onItemSelectedListener = this@AddProductFragment
        }
    }

    private fun bindViewModel(){
        viewModel.supplierLiveData.observe(viewLifecycleOwner){suppliersList ->
            var arrayTitleSupplier = listOf("Select supplier")

            suppliersList.forEach { supplier ->
                arrayTitleSupplier = arrayTitleSupplier + listOf(supplier.title)
            }

            initSpinner(arrayTitleSupplier.toTypedArray())
        }
    }

    private fun getAllSuppliers(){
        viewModel.getAllSuppliers()
    }



    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long){
            supplierId = p3
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}