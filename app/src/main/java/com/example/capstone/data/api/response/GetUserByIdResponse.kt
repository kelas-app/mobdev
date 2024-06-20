package com.example.capstone.data.api.response

import com.google.gson.annotations.SerializedName

data class GetUserByIdResponse(

	@field:SerializedName("firstname")
	val firstname: String? = null,

	@field:SerializedName("address")
	val address: String? = null,

	@field:SerializedName("role")
	val role: String? = null,

	@field:SerializedName("avatar")
	val avatar: String? = null,

	@field:SerializedName("lastname")
	val lastname: String? = null,

	@field:SerializedName("createdat")
	val createdat: String? = null,

	@field:SerializedName("nik")
	val nik: String? = null,

	@field:SerializedName("password")
	val password: String? = null,

	@field:SerializedName("phone")
	val phone: String? = null,

	@field:SerializedName("ratings")
	val ratings: List<RatingsItem?>? = null,

	@field:SerializedName("__v")
	val v: Int? = null,

	@field:SerializedName("_id")
	val id: String? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("username")
	val username: String? = null,

	@field:SerializedName("updatedat")
	val updatedat: String? = null
)

data class RatingsItem(

	@field:SerializedName("comment")
	val comment: String? = null,

	@field:SerializedName("_id")
	val id: String? = null,

	@field:SerializedName("value")
	val value: Any? = null
)
