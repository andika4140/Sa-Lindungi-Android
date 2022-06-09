package com.example.sa_lindungi.UI.donation.transaction

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.sa_lindungi.R
import com.example.sa_lindungi.UI.donation.donationDetail.DonationDetailActivity
import com.example.sa_lindungi.databinding.ActivityPaymentBinding

class PaymentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPaymentBinding
    private lateinit var paymentViewModel: PaymentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        setupViewModel()
        setupAction()
    }

    private fun setupViewModel() {
        paymentViewModel = ViewModelProvider(
            this
        )[PaymentViewModel::class.java]
    }

    private fun setupAction() {
        val id = intent.getIntExtra(EXTRA_ID, 0)

        binding.buttonPay.setOnClickListener {
            val email = binding.emailEditText.text.toString()
            val nominal = binding.nominalEditText.text.toString().toInt()
            val bank = binding.etBank.text.toString()
            when {
                email.isEmpty() -> {
                    binding.emailEditTextLayout.error = "Masukkan email"
                }
                nominal == null -> {
                    binding.nominalEditTextLayout.error = "Masukkan nominal transaksi"
                }
                bank.isEmpty() -> {
                    binding.etBankLayout.error = "Masukkan perusahaan bank"
                }
                !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                    binding.emailEditTextLayout.error = "Format email harus sesuai"
                }
                else -> {
                    paymentViewModel.postTransaction(id, bank, email, nominal)
                    Toast.makeText(this, "payment berhasil dan diproses", Toast.LENGTH_SHORT).show()
                }
            }
        }
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