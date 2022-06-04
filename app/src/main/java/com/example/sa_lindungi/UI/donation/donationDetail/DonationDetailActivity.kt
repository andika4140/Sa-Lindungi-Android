package com.example.sa_lindungi.UI.donation.donationDetail

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import com.example.sa_lindungi.R
import com.example.sa_lindungi.UI.home.HomeActivity
import com.example.sa_lindungi.UI.home.MainActivity
import com.example.sa_lindungi.databinding.ActivityDonationDetailBinding
import com.google.gson.annotations.SerializedName

class DonationDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDonationDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDonationDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        setupAction()

    }

    private fun setupAction() {
        binding.buttonHome.setOnClickListener { toHome() }

        val name = intent.getStringExtra(EXTRA_NAMA)
        val desc = intent.getStringExtra(EXTRA_DESKRIPSI)
        val lokasi = intent.getStringExtra(EXTRA_LOKASI)
        val kontak = intent.getStringExtra(EXTRA_KONTAK)
        val website = intent.getStringExtra(EXTRA_WEBSITE)
        val rekening = intent.getStringExtra(EXTRA_REKENING)

        binding.apply {
            tvDonasi.text = name
            donasiDesc.text = desc
            donasiLokasi.text = lokasi
            donasiKontak.text = kontak
            donasiWebsite.text = website
            donasiRekening.text = rekening
        }
    }

    private fun toHome() {
        val intentToMain = Intent(this, HomeActivity::class.java)
        intentToMain.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intentToMain)
        finish()
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
        const val EXTRA_KONTAK = "extra_kontal"
        const val EXTRA_WEBSITE = "extra_website"
        const val EXTRA_NAMA = "extra_nama"
        const val EXTRA_REKENING = "extra_rekening"
        const val EXTRA_LOKASI = "extra_lokasi"
        const val EXTRA_ID = 0
        const val EXTRA_DESKRIPSI = "extra_deskripsi"
        const val EXTRA_GAMBAR = "extra_gambar"
    }

}