package com.example.roomdaodatabase.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.roomdaodatabase.R
import com.example.roomdaodatabase.databinding.FragmentStartingBinding

class StartingFragment: Fragment(R.layout.fragment_starting) {
    private var _binding: FragmentStartingBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentStartingBinding.bind(view)

        binding.shopsButton.setOnClickListener {
            findNavController().navigate(R.id.action_startingFragment_to_shopsListFragment)
        }
        binding.suppliersButton.setOnClickListener {
            findNavController().navigate(R.id.action_startingFragment_to_suppliersListFragment)
        }
        binding.productsButton.setOnClickListener {
            findNavController().navigate(R.id.action_startingFragment_to_productsListFragment)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}