package com.example.capstone.view.cart.riwayat

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.content.ContextCompat
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
        private val textViewPrice: TextView = itemView.findViewById(R.id.tvProductPrice) // Sesuaikan dengan ID yang ada di item_riwayat_pesanan.xml
        private val removeButton: ImageButton = itemView.findViewById(R.id.btnRemove) // Use ImageButton instead of Button
        private val textViewStatus: TextView = itemView.findViewById(R.id.tvStatus)
        private val buttonOrder: Button = itemView.findViewById(R.id.btnOrder)

        fun bind(data: Order) {
            textViewStatus.visibility = View.VISIBLE
            buttonOrder.visibility = View.GONE
            textViewTitle.text = data.productId // Modify as needed
            textViewPrice.text = context.getString(R.string.rupiah, data.totalPrice)
            textViewStatus.text = data.status
            removeButton.visibility = View.INVISIBLE
            if (data.status == "Batal") {
                //.setBackgroundColor(ContextCompat.getColor(context, R.color.red))
                textViewStatus.setTextColor(ContextCompat.getColor(context, R.color.red))

            } else {
                // Reset background color if status is not "Batal"
                textViewStatus.setTextColor(ContextCompat.getColor(context, R.color.green))
            }
            // Implementasi binding data lainnya di sini jika diperlukan
        }
    }

    fun updateData(newDataList: List<Order>) {
        dataList = newDataList
        notifyDataSetChanged()
    }
}
