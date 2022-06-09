package com.example.sa_lindungi.UI.donation.transaction

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sa_lindungi.UI.api.ApiConfig
import com.example.sa_lindungi.UI.api.response.TransactionResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PaymentViewModel : ViewModel() {

    private val _id = MutableLiveData<Int>()
    var id = 0
//    val id: LiveData<Int> = _id

    fun postTransaction(id: Int, bank: String, email: String, nominal: Int) {
        val service = ApiConfig.getApiService().postTransaction(id, bank, email, nominal)
        service.enqueue(object : Callback<TransactionResponse> {
            override fun onResponse(
                call: Call<TransactionResponse>,
                response: Response<TransactionResponse>
            ) {
                if (response.isSuccessful) {
                    Log.d(TAG, "onSuccess: ${response.message()}")
                } else {
                    Log.d(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<TransactionResponse>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message}")
            }

        })
    }

    companion object {
        private const val TAG = "PaymentViewModel"
    }
}