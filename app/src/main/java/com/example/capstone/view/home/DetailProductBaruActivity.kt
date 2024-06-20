package com.example.capstone.view.home

import android.content.Intent
import android.nfc.cardemulation.CardEmulation.EXTRA_CATEGORY
import android.os.Bundle
import androidx.activity.viewModels
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.bumptech.glide.Glide
import com.example.capstone.R
import com.example.capstone.databinding.ActivityDetailProductBaruBinding
import com.example.capstone.di.factory.ViewModelFactory
import com.example.capstone.view.main.MainActivity

class DetailProductBaruActivity : AppCompatActivity() {

    private val viewModel by viewModels<DetailProductBaruViewModel> {
        ViewModelFactory.getInstance(this)
    }

    private lateinit var binding: ActivityDetailProductBaruBinding

    companion object{
        const val EXTRA_PRODUCT_ID = "extra_product_id"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailProductBaruBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val productId = intent.getStringExtra(EXTRA_PRODUCT_ID)
        if (productId != null) {
            viewModel.fetchProductDetails(productId)
        }

        observeViewModel()

        binding.buttonback.setOnClickListener {
            val intent = Intent(this ,MainActivity::class.java)
                startActivity(intent)
        }

    }
        private fun observeViewModel() {
        viewModel.productDetails.observe(this) { product ->
            binding.title.text = product.name
            binding.category.text = product.category
            binding.price.text = product.price.toString()
            binding.description.text = product.description
            Glide.with(this)
                .load(product.productImage)
                .placeholder(R.drawable.detailkursi) // Default image while loading
                .error(R.drawable.detailkursi) // Default image if there is an error or null
                .into(binding.imgItemPhoto)
        }
    }
}
