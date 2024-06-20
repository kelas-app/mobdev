package com.example.capstone.data.api.response

import com.google.gson.annotations.SerializedName

data class ConversationsResponse(

	@field:SerializedName("ConversationsResponse")
	val conversationsResponse: List<ConversationsResponseItem?>? = null
)

data class ConversationsResponseItem(

	@field:SerializedName("lastMessageAt")
	val lastMessageAt: String? = null,

	@field:SerializedName("__v")
	val v: Int? = null,

	@field:SerializedName("lastMessage")
	val lastMessage: String? = null,

	@field:SerializedName("_id")
	val id: String? = null,

	@field:SerializedName("participants")
	val participants: List<String?>? = null
)
