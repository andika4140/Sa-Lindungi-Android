package com.example.sa_lindungi.UI.api

import com.example.sa_lindungi.UI.api.response.*
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    //semua satwa
    @GET("/satwa")
    fun getAllSatwa(): Call<SatwaResponse>

    //satwa by id
    @GET("/satwa/v2/{id}")
    fun getSatwa(
        @Path("id") id: Int
    ): Call<SatwaResponseItem>

    //semua donasi
    @GET("/donasi")
    fun getAllDonasi(): Call<List<DonasiResponseItem>>

    //donasi by id
    @GET("/donasi/{id}")
    fun getDonasi(
        @Path("id") id: Int
    ): Call<SatwaResponseItem>

    //predict
    @Multipart
    @POST("/predict")
    fun postPredict(
        @Part file: MultipartBody.Part
    ): Call<PredictResponse>

    //satwa-donasi
    @GET("/satwa-donasi/v2/satwa/{id}")
    fun getSatwaDonasi(
        @Path("id") id: Int
    ): Call<List<DonasiResponseItem>>

    //post Transaction
    @FormUrlEncoded
    @POST("/transaksi")
    fun postTransaction(
        @Field("id") id: Int,
        @Field("bank") bank: String,
        @Field("email") email: String,
        @Field("nominal") nominal: Int
    ): Call<TransactionResponse>

    //list transaction by email
    @GET("/transaksi/email/{email}")
    fun getListTransaction(
        @Path("email") email: String
    ): Call<List<TransactionListResponseItem>>

    //GET Transaction detail by id transaksi
    @GET("/transaksi/status/{id}")
    fun getTransactionDetail(
        @Path("id") id: String
    ): Call<TransactionDetailResponse>
}