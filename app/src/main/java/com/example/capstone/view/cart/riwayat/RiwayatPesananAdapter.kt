package com.example.capstone.view.cart.riwayat

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.capstone.R
import com.example.capstone.data.api.response.Order

class RiwayatPesananAdapter(private val context: Context, private var dataList: List<Order>) :
    RecyclerView.Adapter<RiwayatPesananAdapter.ViewHolder>() {

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
        private val textViewTitle: TextView = itemView.findViewById(R.id.tvProductTitle) // Sesuaikan dengan ID yang ada di item_riwayat_pesanan.xml

        fun bind(data: Order) {
            textViewTitle.text = "${data.productId} - ${data.totalPrice}" // Modify as needed
            // Implementasi binding data lainnya di sini jika diperlukan
        }
    }

    fun updateData(newDataList: List<Order>) {
        dataList = newDataList
        notifyDataSetChanged()
    }
}
