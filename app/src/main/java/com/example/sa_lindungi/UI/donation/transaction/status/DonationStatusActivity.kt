package com.example.sa_lindungi.UI.donation.transaction.status

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.example.sa_lindungi.R
import com.example.sa_lindungi.UI.api.response.TransactionDetailResponse
import com.example.sa_lindungi.UI.donation.transaction.listTransaction.ListTransactionActivity
import com.example.sa_lindungi.UI.home.HomeActivity
import com.example.sa_lindungi.UI.scanAnimal.result.ResultActivity
import com.example.sa_lindungi.databinding.ActivityDonationStatusBinding

class DonationStatusActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDonationStatusBinding
    private lateinit var donationStatusViewModel: DonationStatusViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDonationStatusBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        setupViewModel()

        binding.buttonHome.setOnClickListener { toHome() }
        binding.backButton.setOnClickListener { toBack() }
    }

    private fun toBack() {
        finish()
    }

    private fun toHome() {
        AlertDialog.Builder(this@DonationStatusActivity).apply {
            setTitle("Terima Kasih!")
            setMessage("Dengan bantuan donasi kamu, kamu telah membantu satwa langka di Indonesia")
            setPositiveButton("Home") { _, _ ->
                val intentToMain = Intent(context, HomeActivity::class.java)
                intentToMain.flags =
                    Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intentToMain)
                finish()
            }
            create()
            show()
        }
    }

    private fun setupViewModel() {
        donationStatusViewModel = ViewModelProvider(
            this
        )[DonationStatusViewModel::class.java]

        val id = intent.getStringExtra(EXTRA_ID)

        donationStatusViewModel.isLoading.observe(this, {
            showLoading(it)
        })

        donationStatusViewModel.statusDonation.observe(this, { statusDonation ->
            setStatusDonationData(statusDonation)
        })

        donationStatusViewModel.getStatusDonation(id!!)
    }

    private fun setStatusDonationData(statusDonation: TransactionDetailResponse) {
        binding.apply {
            bank.text = statusDonation.bank
            idPembayaran.text = "ID Pembayaran\n${statusDonation.id}"
            email.text = "Email\n${statusDonation.email}"
            vaNumber.text = statusDonation.vaNumber
            nominal.text = "Total Pembayaran\nRp${statusDonation.grossAmount}"
            namaDonasi.text = statusDonation.donasi
            time.text = statusDonation.transactionTime
            expired.text = statusDonation.transactionExpired
            status.text = statusDonation.transactionStatus
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

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.INVISIBLE
        }
    }

    companion object {
        const val EXTRA_ID = "extra_id"
    }
}