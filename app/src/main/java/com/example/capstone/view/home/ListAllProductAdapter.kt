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
import com.example.capstone.data.api.response.GetAllProductResponseItem

class ListAllProductAdapter : ListAdapter<GetAllProductResponseItem, ListAllProductAdapter.ProductViewHolder>(DIFF_CALLBACK) {

    val TAG = "ListAllProductAdapter"

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.title)
        val category: TextView = itemView.findViewById(R.id.category)
        val price: TextView = itemView.findViewById(R.id.price)
        val imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.card_rv_home, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = getItem(position)
        holder.title.text = product.name
        holder.category.text = product.category
        //holder.price.text = product.price?.toString() ?: "NA"
        holder.price.text = String.format(holder.itemView.context.getString(R.string.rupiah), product.price)


        Glide.with(holder.itemView.context)
            .load(product.productImage?.get(0))
            .placeholder(R.drawable.detailkursi) // Default image while loading
            .error(R.drawable.detailkursi) // Default image if there is an error or null
            .into(holder.imgPhoto)

        holder.itemView.setOnClickListener {
            val intentDetail = Intent(holder.itemView.context, DetailProductBaruActivity::class.java).apply {
                putExtra(DetailProductBaruActivity.EXTRA_PRODUCT_ID, product.id)
                putExtra(DetailProductBaruActivity.EXTRA_PRODUCT_SELLER, product.sellerId)
            }
            holder.itemView.context.startActivity(intentDetail)
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<GetAllProductResponseItem>() {
            override fun areItemsTheSame(oldItem: GetAllProductResponseItem, newItem: GetAllProductResponseItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: GetAllProductResponseItem, newItem: GetAllProductResponseItem): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}