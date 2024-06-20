package com.example.capstone.view.chat

import android.content.Intent
import android.util.Log
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
import com.example.capstone.data.api.response.ConversationsResponseItem

class ListChatAdapter: ListAdapter<ConversationsResponseItem, ListChatAdapter.ListViewHolder>(DIFF_CALLBACK) {
    val TAG = "ListchatsAdapter"
    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val imgChat : ImageView = itemView.findViewById(R.id.img_chat)
        val usernameChat : TextView = itemView.findViewById(R.id.usernameChat)
        val lastMessage : TextView = itemView.findViewById(R.id.lastMessage)
        val timeMessage : TextView = itemView.findViewById(R.id.timeMessage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        Log.d(TAG , "OnCreateViewHolder called")
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.card_rv_conversation,parent,false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val chat = getItem(position)
        Log.d(TAG, "OnBindViewHolder called for position : $position")
        holder.lastMessage.text = chat.lastMessage
        holder.timeMessage.text = chat.lastMessageAt
//        Glide.with(holder.itemView.context)
//                .load(chat.chatImage[0])
//                .into(holder.imgPhoto)
//        Glide.with(holder.itemView.context)
//            .load(chat.chatImage?.get(0))
//            .placeholder(R.drawable.detailkursi) // Default image while loading
//            .error(R.drawable.detailkursi) // Default image if there is an error or null
//            .into(holder.imgPhoto)


//        holder.itemView.setOnClickListener {
//            val intentDetail = Intent(holder.itemView.context,
//                DetailChat::class.java).apply {
//                putExtra(DetailChat.EXTRA_chat_ID, chat.id)
//            }
//            holder.itemView.context.startActivity(intentDetail)
//        }

    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ConversationsResponseItem>() {
            override fun areItemsTheSame(oldItem: ConversationsResponseItem, newItem: ConversationsResponseItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: ConversationsResponseItem, newItem: ConversationsResponseItem): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}