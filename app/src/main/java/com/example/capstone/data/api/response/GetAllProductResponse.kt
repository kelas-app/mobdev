package com.example.capstone.data.api.response

import com.google.gson.annotations.SerializedName

data class GetAllProductResponse(

	@field:SerializedName("GetAllProductResponse")
	val getAllProductResponse: List<GetAllProductResponseItem> = emptyList()
)

data class GetAllProductResponseItem(

	@field:SerializedName("sellerId")
	val sellerId: String? = null,

	@field:SerializedName("productImage")
	val productImage: List<String?>? = null,

	@field:SerializedName("price")
	val price: Any? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("category")
	val category: String? = null
)
