package com.example.capstone.view.cart.keranjang

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.capstone.databinding.FragmentKeranjangBinding

class KeranjangFragment : Fragment() {

    private var _binding: FragmentKeranjangBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentKeranjangBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // RecyclerView setup (dummy setup)
        val recyclerViewKeranjang: RecyclerView = binding.recyclerViewKeranjang
        recyclerViewKeranjang.layoutManager = LinearLayoutManager(requireContext())
        recyclerViewKeranjang.adapter = KeranjangAdapter(requireContext(), mutableListOf()) // Dummy adapter, sesuaikan dengan kebutuhan Anda

        // Dummy total harga (sesuaikan dengan logika Anda)
        binding.tvTotalHarga.text = "$100" // Contoh

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
