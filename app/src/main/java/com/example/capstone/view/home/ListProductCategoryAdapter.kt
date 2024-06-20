package com.example.capstone.view.home

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.capstone.R
import com.example.capstone.data.api.response.GetCategoryProductResponseItem
import com.example.capstone.view.home.ListProductsAdapter.Companion.DIFF_CALLBACK

class ListProductCategoryAdapter:  ListAdapter<GetCategoryProductResponseItem, ListProductCategoryAdapter.CategoryViewHolder>(DIFF_CALLBACK) {

    val TAG = "CategoryProductsAdapter"

    class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.title)
        val category: TextView = itemView.findViewById(R.id.category)
        val price: TextView = itemView.findViewById(R.id.price)
        val imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.card_rv_home, parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val product = getItem(position)
        holder.title.text = product.name
        holder.category.text = product.category
        holder.price.text = product.price?.toString() ?: "NA"

        Glide.with(holder.itemView.context)
            .load(product.productImage?.get(0))
            .placeholder(R.drawable.detailkursi) // Default image while loading
            .error(R.drawable.detailkursi) // Default image if there is an error or null
            .into(holder.imgPhoto)

        holder.itemView.setOnClickListener {
            val intentDetail = Intent(holder.itemView.context, DetailProductBaruActivity::class.java).apply {
                putExtra(DetailProductBaruActivity.EXTRA_PRODUCT_ID, product.id)
            }
            holder.itemView.context.startActivity(intentDetail)
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<GetCategoryProductResponseItem>() {
            override fun areItemsTheSame(oldItem: GetCategoryProductResponseItem, newItem: GetCategoryProductResponseItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: GetCategoryProductResponseItem, newItem: GetCategoryProductResponseItem): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}