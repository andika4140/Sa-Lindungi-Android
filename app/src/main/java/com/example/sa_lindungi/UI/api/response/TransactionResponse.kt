package com.example.sa_lindungi.UI.api.response

import com.google.gson.annotations.SerializedName

data class TransactionResponse(

	@field:SerializedName("donasi")
	val donasi: String? = null,

	@field:SerializedName("transaction_status")
	val transactionStatus: String? = null,

	@field:SerializedName("bank")
	val bank: String? = null,

	@field:SerializedName("transaction_time")
	val transactionTime: String? = null,

	@field:SerializedName("transaction_expired")
	val transactionExpired: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("gross_amount")
	val grossAmount: String? = null,

	@field:SerializedName("va_number")
	val vaNumber: String? = null,

	@field:SerializedName("email")
	val email: String? = null
)