package com.example.capstone.view.chat

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.capstone.databinding.FragmentChatBinding
import com.example.capstone.di.factory.ViewModelFactory

class ChatFragment : Fragment() {
    companion object {
        const val EXTRA_SELLER_ID = "extra_seller_id"

        fun newInstance(sellerId: Array<String>): ChatFragment {
            val fragment = ChatFragment()
            val args = Bundle()
            args.putStringArray(EXTRA_SELLER_ID, sellerId)
            fragment.arguments = args
            return fragment
        }
    }

    private var _binding: FragmentChatBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: ListChatAdapter
    private val viewModel by viewModels<ChatViewModel> {
        ViewModelFactory.getInstance(requireContext().applicationContext)
    }

    private val TAG = "ChatFragment"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChatBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root
    }

    private fun fetchUserId() {
//        val sellerId = arguments?.getStringArray(EXTRA_SELLER_ID)
//        showLoading(true)
//        viewModel.getUserId().observe(viewLifecycleOwner) { userId ->
//            if (userId != null) {
//                createConversation(sellerId, userId)
//                Log.d(TAG, "UserId : $userId fetched for create Conversation")
//            } else {
//                Log.d(TAG, "UserId is null")
//            }
//        }
    }

    private fun createConversation(sellerId: String, userId: String) {
        val participants = listOf(sellerId, userId)
        viewModel.createConversation(participants).observe(viewLifecycleOwner) { result ->
            result.fold(
                onSuccess = { conversation ->
                    Log.d(TAG, "Conversation created: $conversation")
                    // Handle successful conversation creation
                    showLoading(false)
                },
                onFailure = { exception ->
                    Log.e(TAG, "Error creating conversation: ${exception.message}")
                    // Handle error
                    showLoading(false)
                }
            )
        }
    }

    private fun setUpRecyclerView() {
        showLoading(true)
        Log.d(TAG, "setUpRecyclerView called")
        adapter = ListChatAdapter()
        binding.rvConversation.layoutManager = LinearLayoutManager(requireContext())
        binding.rvConversation.adapter = adapter
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.progressbar.visibility = View.VISIBLE
        } else {
            binding.progressbar.visibility = View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}