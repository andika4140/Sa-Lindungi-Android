package com.example.sa_lindungi.UI.api.response

import com.google.gson.annotations.SerializedName

data class DonasiResponse(

	@field:SerializedName("DonasiResponse")
	val donasiResponse: List<DonasiResponseItem>
)

data class DonasiResponseItem(

	@field:SerializedName("kontak")
	val kontak: String? = null,

	@field:SerializedName("website")
	val website: String? = null,

	@field:SerializedName("nama")
	val nama: String? = null,

	@field:SerializedName("rekening")
	val rekening: String? = null,

	@field:SerializedName("lokasi")
	val lokasi: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("deskripsi")
	val deskripsi: String? = null,

	@field:SerializedName("gambar")
	val gambar: String? = null,

	@field:SerializedName("logo")
	val logo: String? = null
)