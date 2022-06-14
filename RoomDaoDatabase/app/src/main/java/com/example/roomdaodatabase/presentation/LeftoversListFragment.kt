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
import com.example.roomdaodatabase.adapter.LeftoversAdapter
import com.example.roomdaodatabase.data.models.Order
import com.example.roomdaodatabase.databinding.FragmentLeftoversListBinding

class LeftoversListFragment: Fragment(R.layout.fragment_leftovers_list) {
    private var _binding: FragmentLeftoversListBinding? = null
    private val binding get() = _binding!!
    private val args: LeftoversListFragmentArgs by navArgs()
    private val viewModel: SharedViewModel by viewModels()
    private var leftoversAdapter: LeftoversAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentLeftoversListBinding.bind(view)

        initList()
        bindViewModel()
        getLeftoversByShopId()

        binding.addProductButton.setOnClickListener {
            findNavController().navigate(LeftoversListFragmentDirections
                .actionLeftoversListFragmentToAddLeftoversProductFragment(args.shopId))
        }

    }

    private fun initList(){
        leftoversAdapter = LeftoversAdapter {productId, supplierId ->
            //Log.d("testR", "${Order(0, args.shopId, supplierId, productId, 10)}" )
                viewModel.insertOrder(args.shopId, supplierId, productId)
        }
        with(binding.leftoversList){
            adapter = leftoversAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
    }
    private fun bindViewModel(){
        viewModel.leftoversLiveData.observe(viewLifecycleOwner){
            leftoversAdapter?.items = it
        }
    }
    private fun getLeftoversByShopId(){
        viewModel.getLeftoversByShopId(args.shopId)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        leftoversAdapter = null
    }
}