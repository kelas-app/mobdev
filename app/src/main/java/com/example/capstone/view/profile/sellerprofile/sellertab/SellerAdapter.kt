package com.example.capstone.view.profile.sellerprofile.sellertab

import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.example.capstone.R
import com.example.capstone.data.api.services.ProductRequest

class SellerAdapter(var items: List<ProductRequest>) :
    RecyclerView.Adapter<SellerAdapter.ViewHolder>() {
    private var showCompleted = true

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgItemPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        val title: TextView = itemView.findViewById(R.id.title)
        val category: TextView = itemView.findViewById(R.id.category)
        val price: TextView = itemView.findViewById(R.id.price)
        val btnMarkComplete: Button = itemView.findViewById(R.id.btnPesananSelesai)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_rv_seller, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.title.text = item.name
        holder.category.text = item.category
        holder.price.text = item.price.toString()

        holder.btnMarkComplete.visibility = if (!showCompleted || item.isCompleted) View.GONE else View.VISIBLE

        holder.btnMarkComplete.setOnClickListener {
            item.isCompleted = true
            notifyDataSetChanged()
        }

        /*if (item.productImage.isNotEmpty()) {
            Glide.with(holder.itemView.context)
                .load(item.productImage[0])
                .placeholder(R.drawable.placeholder_image)
                .override(500,500) // Adjust based on column count
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?, model: Any?,
                        target: com.bumptech.glide.request.target.Target<Drawable>?, isFirstResource: Boolean
                    ): Boolean {
                        Log.e("GlideError", "Error loading image: ${item.productImage[0]}", e)
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?, model: Any?,
                        target: com.bumptech.glide.request.target.Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean
                    ): Boolean {
                        return false
                    }
                })
                .into(holder.imgItemPhoto)
        }*/
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setItems(newItems: List<ProductRequest>, showCompleted: Boolean) {
        this.showCompleted = showCompleted
        items = newItems
        notifyDataSetChanged()
    }
}
