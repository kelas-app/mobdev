package com.example.capstone.view.cart.keranjang

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.capstone.databinding.FragmentKeranjangBinding
import com.example.capstone.di.Injection

class KeranjangFragment : Fragment() {

    private lateinit var viewModel: KeranjangViewModel
    private var _binding: FragmentKeranjangBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentKeranjangBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val factory = Injection.provideViewModelFactory(requireContext())
        viewModel = ViewModelProvider(this, factory).get(KeranjangViewModel::class.java)

        val recyclerViewKeranjang: RecyclerView = binding.recyclerViewKeranjang
        recyclerViewKeranjang.layoutManager = LinearLayoutManager(requireContext())

        viewModel.products.observe(viewLifecycleOwner) { productList ->
            recyclerViewKeranjang.adapter = KeranjangAdapter(requireContext(), productList)
            // Update total price or other UI elements here
        }

        viewModel.fetchCartItems()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}