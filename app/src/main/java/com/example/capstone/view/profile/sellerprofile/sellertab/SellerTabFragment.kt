package com.example.capstone.view.profile.sellerprofile.sellertab

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.capstone.R
import com.example.capstone.view.profile.sellerprofile.sellertab.addproduct.ProductActivity

class SellerTabFragment : Fragment() {

    private lateinit var sellerViewModel: SellerViewModel
    private lateinit var sellerAdapter: SellerAdapter
    private lateinit var rvSellerProfile: RecyclerView
    private lateinit var dijualButton: Button
    private lateinit var selesaiButton: Button
    private lateinit var btnJualBarang: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile_tab_seller, container, false)
        rvSellerProfile = view.findViewById(R.id.rvSellerProfile)
        dijualButton = view.findViewById(R.id.btnDijual)
        selesaiButton = view.findViewById(R.id.btnSelesai)
        btnJualBarang=view.findViewById(R.id.btnJualBarang)

        // Initialize ViewModel
        sellerViewModel = ViewModelProvider(this).get(SellerViewModel::class.java)

        // Setup RecyclerView
        sellerAdapter = SellerAdapter(emptyList())

        val itemWidth = resources.getDimension(R.dimen.item_width) // Define item_width in dimens.xml
        val numberOfColumns = calculateNoOfColumns(requireContext(), itemWidth)
        rvSellerProfile.layoutManager = GridLayoutManager(requireContext(), numberOfColumns) // 2 columns
        rvSellerProfile.adapter = sellerAdapter

        // Observe changes in seller items
        sellerViewModel.sellerItems.observe(viewLifecycleOwner, Observer { items ->
            items?.let {
                Log.d("SellerTabFragment", "Observed items: $it")
                // Update RecyclerView with new data
                sellerAdapter.setItems(it, showCompleted = false)

            }
        })

        dijualButton.setOnClickListener {
            sellerViewModel.filterItems(showCompleted = false)
            sellerAdapter.setItems(sellerAdapter.items, showCompleted = false)
        }

        selesaiButton.setOnClickListener {
            sellerViewModel.filterItems(showCompleted = true)
            sellerAdapter.setItems(sellerAdapter.items, showCompleted = true)
        }

        btnJualBarang.setOnClickListener {
            val context = requireContext() // Get the context from the Fragment
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