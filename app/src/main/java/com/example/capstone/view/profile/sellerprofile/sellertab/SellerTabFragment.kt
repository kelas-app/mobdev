package com.example.capstone.view.profile.sellerprofile.sellertab

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.capstone.R
import com.example.capstone.data.api.services.ProductRequest
import com.example.capstone.view.profile.sellerprofile.sellertab.addproduct.ProductActivity

class SellerTabFragment : Fragment() {

    private lateinit var sellerViewModel: SellerViewModel
    private lateinit var sellerAdapter: SellerAdapter
    private lateinit var rvSellerProfile: RecyclerView
    private lateinit var dijualButton: Button
    private lateinit var selesaiButton: Button
    private lateinit var btnProses: Button
    private lateinit var btnJualBarang: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_profile_tab_seller, container, false)
        rvSellerProfile = view.findViewById(R.id.rvSellerProfile)
        dijualButton = view.findViewById(R.id.btnDijual)
        selesaiButton = view.findViewById(R.id.btnSelesai)
        btnProses = view.findViewById(R.id.btnProses)
        btnJualBarang = view.findViewById(R.id.btnJualBarang)

        // Initialize ViewModel
        sellerViewModel = ViewModelProvider(this).get(SellerViewModel::class.java)

        // Setup RecyclerView
        sellerAdapter = SellerAdapter(emptyList())
        val itemWidth = resources.getDimension(R.dimen.item_width) // Define item_width in dimens.xml
        val numberOfColumns = calculateNoOfColumns(requireContext(), itemWidth)
        rvSellerProfile.layoutManager = GridLayoutManager(requireContext(), numberOfColumns)
        rvSellerProfile.adapter = sellerAdapter

        // Load initial data
        sellerViewModel.loadDashboardData()

        // Observe changes in dashboard data
        sellerViewModel.dashboardData.observe(viewLifecycleOwner) { data ->
            data?.let {
                sellerAdapter.setItems(it.dijual.map { item ->
                    ProductRequest(
                        name = item.name,
                        description = item.description,
                        price = item.price,
                        category = item.category,
                        productImage = item.productImage,
                        isVisible = item.isVisible,
                        isCompleted = item.isCompleted,
                        sellerId = item.sellerId,
                        isForSale = true,
                        _id = item.productId
                    )

                }, showCompleted = false)

            }
        }

        dijualButton.setOnClickListener {

            sellerViewModel.dashboardData.value?.let {
                sellerAdapter.setItems(it.dijual.map { item ->
                    ProductRequest(
                        name = item.name,
                        description = item.description,
                        price = item.price,
                        category = item.category,
                        productImage = item.productImage,
                        isVisible = item.isVisible,
                        isCompleted = item.isCompleted,
                        sellerId = item.sellerId,
                        isForSale = true,
                        _id = "item.productId"
                    )
                }, showCompleted = false)
            }
        }
        selesaiButton.setOnClickListener {
            sellerViewModel.dashboardData.value?.let { data ->
                sellerAdapter.setItems(data.selesai.map { item ->
                    ProductRequest(
                        name = item.productId,
                        description = "description", // You might want to adjust this based on actual data
                        price = item.totalPrice,
                        category = item.status,
                        productImage = emptyList(), // Adjust this if your data structure differs
                        isVisible = true, // Assuming this field exists, adjust if necessary
                        isCompleted = true, // Assuming all items in `selesai` are completed
                        sellerId = item.sellerId, // Assuming this field exists
                        isForSale = false,
                        _id = ""
                    )
                }, showCompleted = true)
            }
        }

        btnProses.setOnClickListener {
            sellerViewModel.dashboardData.value?.let { data ->
                sellerAdapter.setItems(data.diproses.map { item ->
                    ProductRequest(
                        name = item.productId,
                        description = "description", // You might want to adjust this based on actual data
                        price = item.totalPrice.toFloat(),
                        category = item.status,
                        productImage = emptyList(), // Adjust this if your data structure differs
                        isVisible = true, // Assuming this field exists, adjust if necessary
                        isCompleted = false, // Assuming all items in `diproses` are not completed
                        sellerId = item.sellerId, // Assuming this field exists
                        isForSale = false,
                        _id = item.productId
                    )
                }, showCompleted = true)
            }
        }

        // INI KALAU GET PRODUCT BY ID BISA SETELAH MASUK STATUS SELESAI/DIPROSES
        /*selesaiButton.setOnClickListener {
            sellerViewModel.dashboardData.value?.let { data ->
                val productIds = data.selesai.map { it.productId }
                sellerViewModel.loadProductDetails(productIds)
            }
        }

        btnProses.setOnClickListener {
            sellerViewModel.dashboardData.value?.let { data ->
                val productIds = data.diproses.map { it.productId }
                sellerViewModel.loadProductDetails(productIds)
            }
        }*/

        sellerViewModel.productDetails.observe(viewLifecycleOwner) { details ->
            details?.let {
                sellerAdapter.setItems(it.map { detail ->
                    ProductRequest(
                        name = detail.name,
                        description = detail.description,
                        price = detail.price.toFloat(),
                        category = detail.category,
                        productImage = detail.productImage,
                        isVisible = detail.isVisible,
                        isCompleted = detail.isCompleted,
                        sellerId = detail.sellerId,
                        isForSale = false,
                        _id = detail.id

                    )
                }, showCompleted = true)
            }
        }

        btnJualBarang.setOnClickListener {
            val context = requireContext()
            val intent = Intent(context, ProductActivity::class.java)
            startActivity(intent)
        }

        return view
    }

    private fun calculateNoOfColumns(context: Context, itemWidth: Float): Int {
        val displayMetrics = context.resources.displayMetrics
        val screenWidthPx = displayMetrics.widthPixels
        return (screenWidthPx / itemWidth).toInt()
    }
}