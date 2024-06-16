package com.example.capstone.data.api.response

import com.google.gson.annotations.SerializedName

data class RegisterResponse(
	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: String? = null,

)


