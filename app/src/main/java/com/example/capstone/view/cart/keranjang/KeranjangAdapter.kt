package com.example.capstone.view.cart.keranjang

import android.content.Context
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
import com.example.capstone.data.api.response.GetDetailProductResponse

class KeranjangAdapter(
    private val context: Context,
    private val dataList: List<GetDetailProductResponse>,
    private val onOrderClick: (GetDetailProductResponse) -> Unit // Add this line
) : RecyclerView.Adapter<KeranjangAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_item_cart, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = dataList[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textViewTitle: TextView = itemView.findViewById(R.id.tvProductTitle)
        private val textViewPrice: TextView = itemView.findViewById(R.id.tvProductPrice)
        private val imageViewProduct: ImageView = itemView.findViewById(R.id.ivProductImage)
        private val orderButton: Button = itemView.findViewById(R.id.btnOrder) // Add Order button

        fun bind(data: GetDetailProductResponse) {
            textViewTitle.text = data.name
            textViewPrice.text = context.getString(R.string.rupiah, data.price)

            orderButton.setOnClickListener {
                onOrderClick(data) // Invoke the click listener
            }

            // Load the image using Glide or any other image loading library
            if (data.productImage.isNotEmpty()) {
                Glide.with(context)
                    .load(data.productImage[0])
                    .placeholder(R.drawable.sample_product_image)
                    .into(imageViewProduct)
            } else {
                imageViewProduct.setImageResource(R.drawable.sample_product_image)
            }
        }
    }
}
