package com.example.capstone.view.chat

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.capstone.data.api.response.ConversationsResponseItem
import com.example.capstone.data.api.response.GetAllProductResponseItem
import com.example.capstone.data.api.response.GetCategoryProductResponseItem
import com.example.capstone.data.api.services.ConversationsRequest
import com.example.capstone.data.pref.UserPreference
import com.example.capstone.data.repository.ProductRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class ChatViewModel (private val productRepository: ProductRepository, private val userPreference: UserPreference) : ViewModel(){

    private val _chat = MutableLiveData<Result<List<ConversationsResponseItem>>>()
    val chat: LiveData<Result<List<ConversationsResponseItem>>> = _chat

    private val _conversation = MutableLiveData<Result<List<ConversationsResponseItem>>>()
    val conversation: LiveData<Result<List<ConversationsResponseItem>>> = _conversation

    fun getAllChat(userId: String): LiveData<Result<List<ConversationsResponseItem>>> {
        val resultFlow = productRepository.getAllChat(userId)
            .onStart { /* Show loading */ }
            .catch { exception -> _chat.postValue(Result.failure(exception)) }

        return resultFlow.asLiveData()
    }



//    fun createConversation(request: ConversationsRequest): LiveData<Result<ConversationsResponseItem>> {
//        viewModelScope.launch {
//            repository.createConversation(request).collect { result ->
//                _conversation.postValue(result)
//            }
//        }
//        return conversation
//    }

    fun createConversation(participants: List<String>): LiveData<Result<List<ConversationsResponseItem>>> {
        viewModelScope.launch {
            val request = ConversationsRequest(participants)
            productRepository.createConversation(request)
                .catch { exception -> _conversation.postValue(Result.failure(exception)) }
                .collect { result -> _conversation.postValue(result) }
        }
        return conversation
    }


    fun getUserId(): LiveData<String?> {
        return userPreference.getSession().map { it.id }.asLiveData()
    }

}