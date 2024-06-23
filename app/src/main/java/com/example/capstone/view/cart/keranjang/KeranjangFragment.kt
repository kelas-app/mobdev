package com.example.capstone.view.cart.keranjang

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.capstone.data.api.response.GetDetailProductResponse
import com.example.capstone.data.api.services.OrderRequest
import com.example.capstone.data.pref.UserPreference
import com.example.capstone.data.pref.dataStore
import com.example.capstone.databinding.FragmentKeranjangBinding
import com.example.capstone.di.Injection
import kotlinx.coroutines.launch

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
            recyclerViewKeranjang.adapter = KeranjangAdapter(requireContext(), productList) { product ->
                createOrder(product)
            }
        }

        viewModel.fetchCartItems()

        return root
    }

    private fun createOrder(product: GetDetailProductResponse) {
        lifecycleScope.launch {
            val userId = UserPreference.getInstance(requireContext().dataStore).getUserId()

            val orderRequest = OrderRequest(
                buyerId = userId,
                sellerId = product.sellerId,
                productId = product.id,
                totalPrice = product.price.toString()
            )
            viewModel.createOrder(orderRequest).observe(viewLifecycleOwner) { orderResponse ->
                if (orderResponse.status == "Proses") {
                    viewModel.fetchCartItems()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}