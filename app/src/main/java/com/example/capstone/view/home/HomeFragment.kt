package com.example.capstone.view.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.capstone.databinding.FragmentHomeBinding
import com.example.capstone.di.factory.ViewModelFactory
import com.example.capstone.view.main.MainViewModel

class HomeFragment : Fragment() {

    private val viewModel by viewModels<HomeViewModel> {
        ViewModelFactory.getInstance(requireContext().applicationContext)
    }

    private var _binding: FragmentHomeBinding? = null
    private lateinit var adapter: ListProductsAdapter

    private val TAG = "HomeFragment"

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.btnDapatsekarang.setOnClickListener{
            val intent = Intent(activity, DetailProductActivity::class.java)
            startActivity(intent)
        }

        setUpRecyclerView()

        return root
    }

    private fun setUpRecyclerView() {
        showLoading(true)
        Log.d(TAG, "setUpRecyclerView called")
        adapter = ListProductsAdapter()

        viewModel.getUserId().observe(viewLifecycleOwner){userId->
            if (userId != null) {
                viewModel.getProducts(userId).observe(viewLifecycleOwner){products->
                    Log.d(TAG, "Products observed ${products.size}")
                    adapter.submitList(products)
                    showLoading(false)
                }
            }
            else{
                Log.d("TAG", "userId null")
            }
        }

        binding.rvHome.layoutManager = GridLayoutManager(requireContext().applicationContext, 2)
        binding.rvHome.adapter = adapter

    }

    private fun showLoading(state: Boolean){
        if (state == true){
            binding.progressbar.visibility = View.VISIBLE
        }
        else{
            binding.progressbar.visibility = View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}