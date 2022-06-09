package com.example.sa_lindungi.UI.home

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import com.example.sa_lindungi.R
import com.example.sa_lindungi.UI.donation.DonationActivity
import com.example.sa_lindungi.UI.donation.transaction.DonationCheckActivity
import com.example.sa_lindungi.UI.scanAnimal.OptionActivity
import com.example.sa_lindungi.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (intent.extras?.getBoolean(EXTRA_BACKSTACK) == true) {
            clearBackstack()
        }
        else {
            binding = ActivityHomeBinding.inflate(layoutInflater)
            setContentView(binding.root)
            setupView()

            binding.buttonKenaliSatwa.setOnClickListener {
                val intentToOption = Intent(this, OptionActivity::class.java)
                startActivity(intentToOption)
            }

            binding.buttonDonasi.setOnClickListener {
                val intentToDonation = Intent(this, DonationActivity::class.java)
                startActivity(intentToDonation)
            }

            binding.buttonCekDonasi.setOnClickListener {
                val intentToCheckDonation = Intent(this, DonationCheckActivity::class.java)
                startActivity(intentToCheckDonation)
            }
        }

    }

    private fun clearBackstack() {
        startActivity(Intent(this, HomeActivity::class.java). apply {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        })
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
        private const val EXTRA_BACKSTACK = "clearBackstack"
    }
}