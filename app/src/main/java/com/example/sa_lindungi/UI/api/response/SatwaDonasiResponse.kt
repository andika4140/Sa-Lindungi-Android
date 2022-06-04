package com.example.sa_lindungi.UI.api.response

import com.google.gson.annotations.SerializedName

data class SatwaDonasiResponse(

	@field:SerializedName("SatwaDonasiResponse")
	val satwaDonasiResponse: List<SatwaDonasiResponseItem?>? = null
)

data class SatwaDonasiResponseItem(

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("DonasiId")
	val donasiId: Int? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("Donasi")
	val donasi: Donasi? = null,

	@field:SerializedName("SatwaId")
	val satwaId: Int? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null,

	@field:SerializedName("Satwa")
	val satwa: Satwa? = null
)

data class Satwa(

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("nama")
	val nama: String? = null,

	@field:SerializedName("lokasi")
	val lokasi: String? = null,

	@field:SerializedName("populasi")
	val populasi: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("funfact")
	val funfact: String? = null,

	@field:SerializedName("nama_saintifik")
	val namaSaintifik: String? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null
)

data class Donasi(

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

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

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null
)
