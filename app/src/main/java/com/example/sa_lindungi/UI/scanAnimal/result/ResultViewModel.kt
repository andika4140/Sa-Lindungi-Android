package com.example.sa_lindungi.UI.scanAnimal.result

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sa_lindungi.UI.api.ApiConfig
import com.example.sa_lindungi.UI.api.response.SatwaResponseItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ResultViewModel : ViewModel() {
    private val _detailSatwa = MutableLiveData<SatwaResponseItem>()
    val detailSatwa: LiveData<SatwaResponseItem> = _detailSatwa

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    companion object {
        private const val TAG = "ResultViewModel"
    }

    fun getSatwaDetail(id: Int) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getSatwa(id)
        client.enqueue(object : Callback<SatwaResponseItem> {
            override fun onResponse(
                call: Call<SatwaResponseItem>,
                response: Response<SatwaResponseItem>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _detailSatwa.value = response.body()
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<SatwaResponseItem>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }
}