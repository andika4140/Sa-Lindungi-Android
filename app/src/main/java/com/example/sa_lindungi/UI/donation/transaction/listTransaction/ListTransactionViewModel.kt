package com.example.sa_lindungi.UI.donation.transaction.listTransaction

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sa_lindungi.UI.api.ApiConfig
import com.example.sa_lindungi.UI.api.response.TransactionListResponseItem
import com.example.sa_lindungi.UI.donation.DonationViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListTransactionViewModel : ViewModel() {
    private val _listTransaction = MutableLiveData<List<TransactionListResponseItem>>()
    val listTransaction: LiveData<List<TransactionListResponseItem>> = _listTransaction

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun getListTransaction(email: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getListTransaction(email)
        client.enqueue(object : Callback<List<TransactionListResponseItem>> {
            override fun onResponse(
                call: Call<List<TransactionListResponseItem>>,
                response: Response<List<TransactionListResponseItem>>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _listTransaction.value = response.body()
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<TransactionListResponseItem>>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }

    companion object {
        private const val TAG = "ListTransactionVM"
    }
}