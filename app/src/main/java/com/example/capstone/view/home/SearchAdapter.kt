package com.example.capstone.view.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.capstone.data.api.response.SearchProductResponseItem
import com.example.capstone.databinding.CardRvSearchBinding

class SearchProductAdapter(
    private val products: List<SearchProductResponseItem>
) : RecyclerView.Adapter<SearchProductAdapter.ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = CardRvSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = products[position]
        holder.bind(product)
    }

    override fun getItemCount(): Int = products.size

    class ProductViewHolder(private val binding: CardRvSearchBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(product: SearchProductResponseItem) {
            binding.productName.text = product.name
            // Bind other product fields here
        }
    }
}