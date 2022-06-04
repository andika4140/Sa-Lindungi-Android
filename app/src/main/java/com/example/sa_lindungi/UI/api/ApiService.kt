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
    @GET("/satwa/{id}")
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

    @Multipart
    @POST("/predict")
    fun postPredict(
        @Part file: MultipartBody.Part
    ): Call<PredictResponse>
}