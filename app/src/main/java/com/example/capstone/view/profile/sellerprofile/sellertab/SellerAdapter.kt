package com.example.capstone.view.profile.sellerprofile.sellertab

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.capstone.R
import com.example.capstone.data.api.services.ProductRequest

class SellerAdapter(
    var items: List<ProductRequest>,
    private val onUpdateStatus: (String, String) -> Unit
) : RecyclerView.Adapter<SellerAdapter.ViewHolder>() {
    private var showCompleted = true

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgItemPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        val title: TextView = itemView.findViewById(R.id.title)
        val category: TextView = itemView.findViewById(R.id.category)
        val price: TextView = itemView.findViewById(R.id.price)
        val btnMarkComplete: Button = itemView.findViewById(R.id.btnPesananSelesai)
        val btnCancel: ImageButton = itemView.findViewById(R.id.btnPesananBatal)

        val btnSetting: ImageButton = itemView.findViewById(R.id.ibSetting)

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
        holder.price.text = String.format(holder.itemView.context.getString(R.string.rupiah), item.price.toDouble())

        holder.btnMarkComplete.visibility = if (!showCompleted || item.isCompleted) View.GONE else View.VISIBLE
        holder.btnCancel.visibility = if (!showCompleted || item.isCompleted) View.GONE else View.VISIBLE

        holder.btnMarkComplete.setOnClickListener {
            item.isCompleted = true
            notifyDataSetChanged()
        }

        holder.btnMarkComplete.setOnClickListener {
            onUpdateStatus(item._id, "Selesai")
        }
        holder.btnCancel.setOnClickListener {
            onUpdateStatus(item._id, "Batal")
        }

        if (item.isForSale) {
            holder.btnSetting.visibility = View.VISIBLE
        } else {
            holder.btnSetting.visibility = View.GONE
        }
        // Load image using Glide
       /* Glide.with(holder.itemView.context)
            .load(item.productImage[0]) // Load image from URL
            .placeholder(R.drawable.placeholder_image) // Placeholder image
            .into(holder.imgItemPhoto) // Load into ImageView*/
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
