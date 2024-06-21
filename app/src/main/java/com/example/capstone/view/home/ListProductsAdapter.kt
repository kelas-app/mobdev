package com.example.capstone.view.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.capstone.R
import com.example.capstone.data.api.response.GetAllProductResponseItem

class ListProductsAdapter : ListAdapter<GetAllProductResponseItem,ListProductsAdapter.ListViewHolder>(DIFF_CALLBACK){
    val TAG = "ListProductsAdapter"
     class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val title : TextView = itemView.findViewById(R.id.title)
        val category : TextView = itemView.findViewById(R.id.category)
        val price : TextView = itemView.findViewById(R.id.price)
        val imgPhoto : ImageView = itemView.findViewById(R.id.img_item_photo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        Log.d(TAG , "OnCreateViewHolder called")
        val view:View = LayoutInflater.from(parent.context).inflate(R.layout.card_rv_home,parent,false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val product = getItem(position)
        Log.d(TAG, "OnBindViewHolder called for position : $position")
        holder.title.text = product.name
        holder.category.text = product.category
        holder.price.text = product.price?.toString()?:"NA"

//        Glide.with(holder.itemView.context)
//                .load(product.productImage[0])
//                .into(holder.imgPhoto)
        Glide.with(holder.itemView.context)
            .load(product.productImage?.get(0))
            .placeholder(R.drawable.detailkursi) // Default image while loading
            .error(R.drawable.detailkursi) // Default image if there is an error or null
            .into(holder.imgPhoto)


        holder.itemView.setOnClickListener {
            val intentDetail = Intent(holder.itemView.context,DetailProductBaruActivity::class.java).apply {
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
