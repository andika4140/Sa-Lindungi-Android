package com.example.sa_lindungi.UI.donation.donationSatwa

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sa_lindungi.UI.api.ApiConfig
import com.example.sa_lindungi.UI.api.response.DonasiResponseItem
import com.example.sa_lindungi.UI.donation.DonationViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DonationSatwaViewModel : ViewModel() {

    private val _listDonationSatwa = MutableLiveData<List<DonasiResponseItem>>()
    val listDonationSatwa: LiveData<List<DonasiResponseItem>> = _listDonationSatwa

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun getListDonation(id: Int) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getSatwaDonasi(id)
        client.enqueue(object : Callback<List<DonasiResponseItem>> {
            override fun onResponse(
                call: Call<List<DonasiResponseItem>>,
                response: Response<List<DonasiResponseItem>>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _listDonationSatwa.value = response.body()
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<DonasiResponseItem>>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }

    companion object {
        private const val TAG = "DonationDetailSatwa"
    }
}