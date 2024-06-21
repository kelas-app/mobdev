package com.example.capstone.view.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.capstone.data.api.response.SearchProductResponseItem
import com.example.capstone.databinding.FragmentHomeBinding
import com.example.capstone.di.factory.ViewModelFactory
import com.example.capstone.view.login.LoginActivity


class HomeFragment : Fragment() {

    private val viewModel by viewModels<HomeViewModel> {
        ViewModelFactory.getInstance(requireContext().applicationContext)
    }

    private var _binding: FragmentHomeBinding? = null
    private lateinit var adapter: ListProductsAdapter

    private lateinit var categoryAdapter: ListProductCategoryAdapter

    private lateinit var allProductAdapter: ListAllProductAdapter

    private lateinit var searchAdapter: SearchProductAdapter

    private val TAG = "HomeFragment"

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        initializeSearch()
        //default
        fetchAllProducts()
        binding.titleRecommendation.text = "All Products"

        binding.recommendation.setOnClickListener{
            fetchRecommendation()
            binding.titleRecommendation.text = "For You"
       }

        binding.all.setOnClickListener{
            fetchAllProducts()
            binding.titleRecommendation.text = "All Products"
        }

        binding.table.setOnClickListener{
            fetchCategoryProducts("Meja")
            binding.titleRecommendation.text = "Table"
        }

        binding.cupboard.setOnClickListener{
            fetchCategoryProducts("Lemari")
            binding.titleRecommendation.text = "CupBoard"
        }
        binding.Bed.setOnClickListener{
            fetchCategoryProducts("Kasur")
            binding.titleRecommendation.text = "Bed"
        }

        binding.fan.setOnClickListener{
            fetchCategoryProducts("Kipas Angin")
            binding.titleRecommendation.text = "Fan"
        }

        return root
    }

    private fun initializeSearch() {
        val searchView = binding.searchBar

        // Set up query listener for search actions
        searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (!query.isNullOrEmpty()) {
                    binding.search.visibility = View.VISIBLE
                    performSearch(query.trim())
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (!newText.isNullOrEmpty()) {
                    performSearch(newText.trim())
                } else {
                    binding.search.visibility = View.GONE
                }
                return true
            }
        })

        searchView.setOnQueryTextFocusChangeListener { _, hasFocus ->
            if (!hasFocus && searchView.query.isEmpty()) {
                binding.search.visibility = View.GONE
            }
        }
    }

    private fun performSearch(searchTerm: String) {
        if (searchTerm.isEmpty()) {
            binding.search.visibility = View.GONE
            return
        }
        showLoading(true)
        viewModel.searchProducts(searchTerm).observe(viewLifecycleOwner) { result ->
            result.fold(
                onSuccess = { products ->
                    // Handle search results, update UI, etc.
                    Log.d(TAG, "Search Results observed $products")
                    setupSearchRecyclerView(products)
                    if (products.isNotEmpty()) {
                        binding.search.visibility = View.VISIBLE
                    } else {
                        binding.search.visibility = View.GONE
                    }
                    showLoading(false)
                },
                onFailure = { exception ->
                    Log.d(TAG, "Error: ${exception.message}")
                    // Handle token expiration or other errors
                    showLoading(false)
                }
            )
        }
    }

    private fun setupSearchRecyclerView(products: List<SearchProductResponseItem>) {
        searchAdapter = SearchProductAdapter(products)
        binding.search.layoutManager = LinearLayoutManager(requireContext())
        binding.search.adapter = searchAdapter
    }


    private fun fetchCategoryProducts(category: String) {
        showLoading(true)
        setUpRecyclerViewCategory()
        observeCategoryViewModel(category)

    }

    private fun fetchRecommendation() {
        showLoading(true)
        setUpRecyclerView()
        observeViewModel()
    }

    private fun fetchAllProducts(){
        showLoading(true)
        setUpRecyclerViewAllProducts()
        observeAllProductViewModel()
    }


    private fun setUpRecyclerViewAllProducts() {
        showLoading(true)
        Log.d(TAG, "setUpRecyclerViewAllProducts called")
        allProductAdapter = ListAllProductAdapter()
        binding.rvHome.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvHome.adapter = allProductAdapter
    }

    private fun setUpRecyclerViewCategory() {
        showLoading(true)
        Log.d(TAG, "setUpRecyclerViewCategory called")
        categoryAdapter = ListProductCategoryAdapter()
        binding.rvHome.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvHome.adapter = categoryAdapter
    }

    private fun setUpRecyclerView() {
        showLoading(true)
        Log.d(TAG, "setUpRecyclerView called")
        adapter = ListProductsAdapter()
        binding.rvHome.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvHome.adapter = adapter
    }

    // viewModelAll
    private fun observeViewModel() {
        viewModel.getUserId().observe(viewLifecycleOwner) { userId ->
            if (userId != null) {
                viewModel.getProducts(userId).observe(viewLifecycleOwner) { result ->
                    result.fold(
                        onSuccess = { products ->
                            Log.d(TAG, "Products observed ${products.size}")
                            adapter.submitList(products)
                            showLoading(false)
                        },
                        onFailure = { exception ->
                            Log.d(TAG, "Error: ${exception.message}")
                            // Handle token expiration
                            if(exception.message == "Token expired") {
                                // Navigate to login screen or show token expired message
                                val intent = Intent(activity, LoginActivity::class.java)
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                                startActivity(intent)
                            }
                            showLoading(false)
                        }
                    )
                }
            } else {
                Log.d(TAG, "userId null")
                showLoading(false)
            }
        }
    }

    private fun observeCategoryViewModel(category: String) {
        viewModel.getCategories(category).observe(viewLifecycleOwner) { result ->
            result.fold(
                onSuccess = { products ->
                    Log.d(TAG, "Products observed ${products.size}")
                    categoryAdapter.submitList(products)
                    showLoading(false)
                            },
                onFailure = { exception ->
                    Log.d(TAG, "Error: ${exception.message}")
                    // Handle token expiration
                    if(exception.message == "Token expired") {
                        // Navigate to login screen or show token expired message
                        val intent = Intent(activity, LoginActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                        startActivity(intent)
                    }
                    showLoading(false)
                }
            )
        }
    }

    private fun observeAllProductViewModel() {
        viewModel.getAllNewProducts().observe(viewLifecycleOwner) { result ->
            result.fold(
                onSuccess = { products ->
                    val sellerId = products.mapNotNull {it.sellerId}
                    Log.d(TAG, "Seller IDs: $sellerId")
                    Log.d(TAG, "Products observed ${products.size}")
                    allProductAdapter.submitList(products)
                    showLoading(false)
                },
                onFailure = { exception ->
                    Log.d(TAG, "Error: ${exception.message}")
                    // Handle token expiration
                    if(exception.message == "Token expired") {
                        // Navigate to login screen or show token expired message
                        val intent = Intent(activity, LoginActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                        startActivity(intent)
                    }
                    showLoading(false)
                }
            )
        }
    }


    private fun showLoading(state: Boolean) {
        if (state) {
            binding.progressbar.visibility = View.VISIBLE
        } else {
            binding.progressbar.visibility = View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}