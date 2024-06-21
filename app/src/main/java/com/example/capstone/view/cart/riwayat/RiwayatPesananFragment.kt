package com.example.capstone.view.cart.riwayat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.capstone.databinding.FragmentRiwayatPesananBinding
import com.example.capstone.di.Injection
class RiwayatPesananFragment : Fragment() {

    private var _binding: FragmentRiwayatPesananBinding? = null
    private val binding get() = _binding!!
    private lateinit var orderViewModel: OrderViewModel
    private lateinit var adapter: RiwayatPesananAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRiwayatPesananBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val factory = Injection.provideViewModelFactory(requireContext())
        orderViewModel = ViewModelProvider(this, factory).get(OrderViewModel::class.java)

        setupRecyclerView()
        fetchOrders()

        return root
    }

    private fun setupRecyclerView() {
        adapter = RiwayatPesananAdapter(requireContext(), mutableListOf())
        val recyclerViewRiwayat: RecyclerView = binding.recyclerViewRiwayat
        recyclerViewRiwayat.layoutManager = LinearLayoutManager(requireContext())
        recyclerViewRiwayat.adapter = adapter
    }

    private fun fetchOrders() {
        orderViewModel.orders.observe(viewLifecycleOwner) { orders ->
            adapter.updateData(orders)
        }
        orderViewModel.getOrders()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
