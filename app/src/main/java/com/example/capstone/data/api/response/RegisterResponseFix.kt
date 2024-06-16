package com.example.capstone.data.api.response

import com.google.gson.annotations.SerializedName

data class RegisterResponseFix(

	@field:SerializedName("data")
	val registerData: RegisterData? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: String? = null,

	@field:SerializedName("token")
	val token: String? = null
)

data class RegisterData(

	@field:SerializedName("firstname")
	val firstname: String? = null,

	@field:SerializedName("address")
	val address: String? = null,

	@field:SerializedName("phone")
	val phone: String? = null,

	@field:SerializedName("_id")
	val id: String? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("lastname")
	val lastname: String? = null,

	@field:SerializedName("username")
	val username: String? = null
)
