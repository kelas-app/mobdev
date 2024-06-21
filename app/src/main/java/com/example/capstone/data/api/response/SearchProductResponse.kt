package com.example.capstone.data.api.response

import com.google.gson.annotations.SerializedName

data class SearchProductResponse(

	@field:SerializedName("SearchProductResponse")
	val searchProductResponse: List<SearchProductResponseItem?>? = null
)

data class SearchProductResponseItem(

	@field:SerializedName("sellerId")
	val sellerId: String? = null,

	@field:SerializedName("productImage")
	val productImage: List<String?>? = null,

	@field:SerializedName("price")
	val price: Int? = null,

	@field:SerializedName("__v")
	val v: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("_id")
	val id: String? = null,

	@field:SerializedName("isVisible")
	val isVisible: Boolean? = null,

	@field:SerializedName("category")
	val category: String? = null,

	@field:SerializedName("isCompleted")
	val isCompleted: Boolean? = null
)
