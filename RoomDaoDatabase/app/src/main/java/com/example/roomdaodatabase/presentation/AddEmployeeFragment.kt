package com.example.roomdaodatabase.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.roomdaodatabase.R
import com.example.roomdaodatabase.data.models.Employee
import com.example.roomdaodatabase.databinding.FragmentAddEmployeeBinding
import java.math.BigDecimal

class AddEmployeeFragment: Fragment(R.layout.fragment_add_employee) {
    private var _binding: FragmentAddEmployeeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SharedViewModel by viewModels()
    private val args: AddEmployeeFragmentArgs by navArgs()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentAddEmployeeBinding.bind(view)

        bindViewModel()

        binding.addEmployeeButton.setOnClickListener {
            val addressEmployee = binding.addressEmployeeEditText.text.toString()
            if (addressEmployee.isNotBlank()){
                viewModel.insertAddressAndGetAddressId(addressEmployee)
            }
        }
    }

    private fun bindViewModel(){
        viewModel.addressIdLiveData.observe(viewLifecycleOwner){
            val nameEmployee = binding.nameEmployeEditText.text.toString()
            val ageEmployee = binding.ageEditText.text.toString().toInt()

            if (nameEmployee.isNotBlank()){
                viewModel.insertEmployee(
                    Employee(
                        id = 0,
                        shopId = args.shopId,
                        name = nameEmployee,
                        addressId = it,
                        age = ageEmployee
                    )
                )
                findNavController().popBackStack()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}