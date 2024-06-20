package com.example.capstone.data.api.response

import com.google.gson.annotations.SerializedName

data class GetAllProductResponseItem(

	@field:SerializedName("_id")
	val _id: String? = null,

	@field:SerializedName("sellerId")
	val sellerId: String? = null,

	@field:SerializedName("productImage")
	val productImage: List<String?>? = null,

	@field:SerializedName("price")
	val price: Float? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("category")
	val category: String? = null,

	@field:SerializedName("isVisible")
	val isVisible: Boolean,

	@field:SerializedName("isCompleted")
	val isCompleted: Boolean
)
