package com.example.sa_lindungi.UI.donation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sa_lindungi.UI.api.ApiConfig
import com.example.sa_lindungi.UI.api.response.DonasiResponseItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DonationViewModel : ViewModel() {
    private val _listDonation = MutableLiveData<List<DonasiResponseItem>>()
    val listDonation: LiveData<List<DonasiResponseItem>> = _listDonation

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun getListDonation() {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getAllDonasi()
        client.enqueue(object : Callback<List<DonasiResponseItem>> {

            override fun onResponse(
                call: Call<List<DonasiResponseItem>>,
                response: Response<List<DonasiResponseItem>>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _listDonation.value = response.body()
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
        private const val TAG = "DonationViewModel"
    }

}