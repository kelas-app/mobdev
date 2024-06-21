package com.example.capstone.view.home

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
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
        const val EXTRA_PRODUCT_SELLER = "extra_product_seller"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailProductBaruBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sellerId = intent.getStringExtra(EXTRA_PRODUCT_SELLER)
        val productId = intent.getStringExtra(EXTRA_PRODUCT_ID)

        if (productId != null) {
            viewModel.fetchProductDetails(productId)
        }

        observeViewModel()

        binding.btnAskSeller.setOnClickListener {
//            pablo@gmail.com

        }

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
                .placeholder(R.drawable.detailkursi)
                .error(R.drawable.detailkursi)
                .into(binding.imgItemPhoto)
        }
    }
}
