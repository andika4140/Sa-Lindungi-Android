package com.example.sa_lindungi.UI.donation.transaction

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import com.example.sa_lindungi.R
import com.example.sa_lindungi.UI.donation.transaction.listTransaction.ListTransactionActivity
import com.example.sa_lindungi.UI.home.HomeActivity
import com.example.sa_lindungi.UI.scanAnimal.result.ResultActivity
import com.example.sa_lindungi.databinding.ActivityDonationCheckBinding

class DonationCheckActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDonationCheckBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDonationCheckBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()

        binding.backButton.setOnClickListener {
            val intentToHome = Intent(this, HomeActivity::class.java)
            intentToHome.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            finish()
        }

        binding.buttonSubmit.setOnClickListener {
            val email = binding.etEmail.text.toString()
            if (email.isEmpty()) {
                Toast.makeText(this, "Isi terlebih dahulu email kamu", Toast.LENGTH_SHORT).show()
            } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                binding.etEmail.error = "Format email harus sesuai"
            } else {
                val intentToTransaction = Intent(this, ListTransactionActivity::class.java)
                intentToTransaction.putExtra(ListTransactionActivity.EXTRA_EMAIL, email)
                startActivity(intentToTransaction)
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
}