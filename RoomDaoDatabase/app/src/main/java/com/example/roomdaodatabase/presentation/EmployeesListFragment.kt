package com.example.roomdaodatabase.presentation

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomdaodatabase.R
import com.example.roomdaodatabase.adapter.AddressAndEmployeeAdapter
import com.example.roomdaodatabase.databinding.FragmentEmployeesListBinding

class EmployeesListFragment: Fragment(R.layout.fragment_employees_list) {
    private var _binding: FragmentEmployeesListBinding? = null
    private val binding get() = _binding!!
    private var addressAndEmployeeAdapter: AddressAndEmployeeAdapter? = null
    private val viewModel: SharedViewModel by viewModels()
    private val args: EmployeesListFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentEmployeesListBinding.bind(view)

        initList()
        bindViewModel()
        getAddressAndEnployees(args.shoId)

        binding.addEmployeeButton.setOnClickListener {
            val action = EmployeesListFragmentDirections
                .actionEmployeesListFragmentToAddEmployeeFragment(args.shoId)
            findNavController().navigate(action)
        }

    }

    private fun initList(){
        addressAndEmployeeAdapter = AddressAndEmployeeAdapter{employeeId ->
            viewModel.removeEmployeeById(employeeId)
            getAddressAndEnployees(args.shoId)
        }
        with(binding.employeesList){
            adapter = addressAndEmployeeAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
    }

    private fun bindViewModel(){
        viewModel.addressAndEmployees.observe(viewLifecycleOwner){
            Log.d("testR", "${it}")
            addressAndEmployeeAdapter?.items = it
        }
    }

    private fun getAddressAndEnployees(shopId: Long){
        viewModel.getAddressAndEmployees(shopId)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        addressAndEmployeeAdapter = null
    }
}