package com.example.capstone.data.api.response

import com.google.gson.annotations.SerializedName

data class GetDetailProductResponse(

	@field:SerializedName("sellerId")
	val sellerId: String,

	@field:SerializedName("productImage")
	val productImage: List<String>,

	@field:SerializedName("price")
	val price: Int,

	@field:SerializedName("__v")
	val v: Int,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("description")
	val description: String,

	@field:SerializedName("_id")
	val id: String,

	@field:SerializedName("isVisible")
	val isVisible: Boolean,

	@field:SerializedName("category")
	val category: String,

	@field:SerializedName("isCompleted")
	val isCompleted: Boolean
)
