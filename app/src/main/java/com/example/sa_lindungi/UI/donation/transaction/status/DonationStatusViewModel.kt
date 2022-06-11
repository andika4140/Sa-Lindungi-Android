package com.example.sa_lindungi.UI.donation.transaction.status

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sa_lindungi.UI.api.ApiConfig
import com.example.sa_lindungi.UI.api.response.TransactionDetailResponse
import com.example.sa_lindungi.UI.scanAnimal.result.ResultViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DonationStatusViewModel : ViewModel() {
    private val _statusDonation = MutableLiveData<TransactionDetailResponse>()
    val statusDonation: LiveData<TransactionDetailResponse> = _statusDonation

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun getStatusDonation(id: String) {
        val client = ApiConfig.getApiService().getTransactionDetail(id)
        client.enqueue(object : Callback<TransactionDetailResponse> {
            override fun onResponse(
                call: Call<TransactionDetailResponse>,
                response: Response<TransactionDetailResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _statusDonation.value = response.body()
                } else {
                    Log.d(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<TransactionDetailResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }

    companion object {
        private const val TAG = "DonationStatusViewModel"
    }
}