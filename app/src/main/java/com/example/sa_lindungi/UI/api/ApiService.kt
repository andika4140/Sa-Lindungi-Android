package com.example.sa_lindungi.UI.api

import com.example.sa_lindungi.UI.api.response.DonasiResponse
import com.example.sa_lindungi.UI.api.response.DonasiResponseItem
import com.example.sa_lindungi.UI.api.response.SatwaResponse
import com.example.sa_lindungi.UI.api.response.SatwaResponseItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

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
}