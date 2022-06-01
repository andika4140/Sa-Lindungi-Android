package com.example.sa_lindungi.UI.api.response

import com.google.gson.annotations.SerializedName

data class SatwaResponse(

	@field:SerializedName("SatwaResponse")
	val satwaResponse: List<SatwaResponseItem?>? = null
)

data class SatwaResponseItem(

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("nama")
	val nama: String? = null,

	@field:SerializedName("lokasi")
	val lokasi: String? = null,

	@field:SerializedName("populasi")
	val populasi: Int? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("funfact")
	val funfact: String? = null,

	@field:SerializedName("nama_saintifik")
	val namaSaintifik: String? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null
)
