package com.example.capstone.view.chat

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.capstone.R
import com.example.capstone.databinding.ActivityChatBinding
import com.example.capstone.di.factory.ViewModelFactory

class ChatActivity : AppCompatActivity() {

//    companion object {
//        const val EXTRA_SELLER_ID = "extra_seller_id"
//    }
//
//    private lateinit var binding: ActivityChatBinding
//    private lateinit var adapter: ListChatAdapter
//
//    private val TAG = "ChatActivity"
//
//    private val viewModel by viewModels<ChatViewModel> {
//        ViewModelFactory.getInstance(applicationContext)
//    }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityChatBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        val sellerId = intent.getStringExtra(EXTRA_SELLER_ID)
//        if (sellerId != null) {
//            Log.d(TAG, "SellerId : $sellerId fetched for create Conversation")
//        } else {
//            Log.d(TAG, "SellerId : $sellerId failed fetched for create Conversation because of null")
//        }
//
//        fetchUserId(sellerId)
//    }
//
//    private fun fetchUserId(sellerId: String?) {
//        showLoading(true)
//        viewModel.getUserId().observe(this) { userId ->
//            if (userId != null && sellerId != null) {
//                createConversation(sellerId, userId)
//                Log.d(TAG, "UserId : $userId and SellerId : $sellerId fetched for create Conversation")
//            } else {
//                Log.d(TAG, "UserId : $userId and SellerId : $sellerId failed fetched for create Conversation because of null")
//            }
//        }
//    }
//
//    private fun createConversation(sellerId: String, userId: String) {
//        val participants = listOf(sellerId, userId)
//        viewModel.createConversation(participants).observe(this) { result ->
//            result.fold(
//                onSuccess = { conversation ->
//                    Log.d(TAG, "Conversation created: $conversation")
//                    // Handle successful conversation creation
//                },
//                onFailure = { exception ->
//                    Log.e(TAG, "Error creating conversation: ${exception.message}")
//                    // Handle error
//                }
//            )
//        }
//        showLoading(false)
//    }
//
//    private fun setUpAllChatRecyclerView() {
//        showLoading(true)
//        Log.d(TAG, "setUpRecyclerView called")
//        adapter = ListChatAdapter()
//        binding.rvConversation.layoutManager = LinearLayoutManager(this)
//        binding.rvConversation.adapter = adapter
//    }
//
//    private fun showLoading(state: Boolean) {
//        if (state) {
//            binding.progressbar.visibility = View.VISIBLE
//        } else {
//            binding.progressbar.visibility = View.GONE
//        }
//    }
}