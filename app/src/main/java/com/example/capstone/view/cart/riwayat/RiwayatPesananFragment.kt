package com.example.capstone.view.cart.riwayat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.capstone.databinding.FragmentRiwayatPesananBinding

class RiwayatPesananFragment : Fragment() {

    private var _binding: FragmentRiwayatPesananBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRiwayatPesananBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // RecyclerView setup (dummy setup)
        val recyclerViewRiwayat: RecyclerView = binding.recyclerViewRiwayat
        recyclerViewRiwayat.layoutManager = LinearLayoutManager(requireContext())
        recyclerViewRiwayat.adapter = RiwayatPesananAdapter(requireContext(), mutableListOf()) // Dummy adapter, sesuaikan dengan kebutuhan Anda

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
