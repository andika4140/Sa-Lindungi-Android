package com.example.sa_lindungi.UI.donation.transaction

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.sa_lindungi.R
import com.example.sa_lindungi.UI.api.ApiConfig
import com.example.sa_lindungi.UI.api.response.TransactionResponse
import com.example.sa_lindungi.UI.donation.transaction.status.DonationStatusActivity
import com.example.sa_lindungi.UI.home.HomeActivity
import com.example.sa_lindungi.databinding.ActivityPaymentBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PaymentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPaymentBinding
    private lateinit var paymentViewModel: PaymentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        setupViewModel()
        setupDropdown()
        setupAction()
    }

    private fun setupViewModel() {
        paymentViewModel = ViewModelProvider(
            this
        )[PaymentViewModel::class.java]
    }

    private fun setupAction() {
        val id = intent.getIntExtra(EXTRA_ID, 0)

        binding.backButton.setOnClickListener {
            val intentToHome = Intent(this, HomeActivity::class.java)
            intentToHome.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            finish()
        }

        binding.buttonPay.setOnClickListener {
            val email = binding.emailEditText.text.toString()
            val nominal = binding.nominalEditText.text.toString()
            val bank = binding.dropdown.text.toString()
            when {
                email.isEmpty() -> {
                    binding.emailEditTextLayout.error = "Masukkan email"
                }
                nominal.isEmpty() -> {
                    binding.nominalEditTextLayout.error = "Masukkan nominal transaksi"
                }
                bank.isEmpty() -> {
                    binding.dropdownLayout.error = "Pilih bank terlebih dahulu"
                }
                !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                    binding.emailEditTextLayout.error = "Format email harus sesuai"
                }
                else -> {
                    val service = ApiConfig.getApiService().postTransaction(id, bank, email, nominal.toInt())
                    service.enqueue(object : Callback<TransactionResponse> {
                        override fun onResponse(
                            call: Call<TransactionResponse>,
                            response: Response<TransactionResponse>
                        ) {
                            if (response.isSuccessful) {
                                val id = response.body()?.id
                                Log.d(TAG, "onSuccess: ${response.message()}")
                                val intentToStatus = Intent(this@PaymentActivity, DonationStatusActivity::class.java)
                                intentToStatus.putExtra(DonationStatusActivity.EXTRA_ID, id)
                                startActivity(intentToStatus)
                            } else {
                                Log.d(TAG, "onFailure: ${response.message()}")
                            }
                        }
                        override fun onFailure(call: Call<TransactionResponse>, t: Throwable) {
                            Log.d(TAG, "onFailure: ${t.message}")
                        }
                    })
                    Toast.makeText(this, "payment berhasil dan diproses", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun setupDropdown() {
        val bankList = listOf("bca", "bni", "bri")
        val adapterDropdown = ArrayAdapter(this, R.layout.item_bank, bankList)
        binding.dropdown.setAdapter(adapterDropdown)
    }

    private fun setupView() {
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        supportActionBar?.hide()
    }

    companion object {
        private const val TAG = "PaymentActivity"
        const val EXTRA_ID = "extra_id"
    }
}