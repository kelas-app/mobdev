package com.example.capstone.view.home

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.capstone.R
import com.example.capstone.data.api.response.GetAllProductResponseItem

class ListProductsAdapter : ListAdapter<GetAllProductResponseItem,ListProductsAdapter.ListViewHolder > (StoryDiffCallback()){
    val TAG = "ListProductsAdapter"
    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val title : TextView = itemView.findViewById(R.id.title)
        val category : TextView = itemView.findViewById(R.id.category)
        val price : TextView = itemView.findViewById(R.id.price)
        val description : TextView = itemView.findViewById(R.id.description)
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
        holder.description.text = product.description
        Glide.with(holder.itemView.context)
            .load(product.productImage)
            .into(holder.imgPhoto)

    }
    class StoryDiffCallback : DiffUtil.ItemCallback<GetAllProductResponseItem>() {
        override fun areItemsTheSame(oldItem: GetAllProductResponseItem, newItem: GetAllProductResponseItem): Boolean {
            return oldItem._id == newItem._id
        }

        override fun areContentsTheSame(oldItem: GetAllProductResponseItem, newItem: GetAllProductResponseItem): Boolean {
            return oldItem == newItem
        }
    }
}
