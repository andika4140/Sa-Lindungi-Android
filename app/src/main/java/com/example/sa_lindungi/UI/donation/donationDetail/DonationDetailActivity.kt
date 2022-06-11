package com.example.sa_lindungi.UI.donation.donationDetail

import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import androidx.appcompat.app.AlertDialog
import com.bumptech.glide.Glide
import com.example.sa_lindungi.R
import com.example.sa_lindungi.UI.donation.DonationActivity
import com.example.sa_lindungi.UI.donation.transaction.PaymentActivity
import com.example.sa_lindungi.UI.home.HomeActivity
import com.example.sa_lindungi.UI.home.MainActivity
import com.example.sa_lindungi.UI.scanAnimal.result.ResultActivity
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

        val id = intent.getIntExtra(EXTRA_ID, 0)
        val name = intent.getStringExtra(EXTRA_NAMA)
        val desc = intent.getStringExtra(EXTRA_DESKRIPSI)
        val lokasi = intent.getStringExtra(EXTRA_LOKASI)
        val kontak = intent.getStringExtra(EXTRA_KONTAK)
        val website = intent.getStringExtra(EXTRA_WEBSITE)
        val rekening = intent.getStringExtra(EXTRA_REKENING)
        val gambar = intent.getStringExtra(EXTRA_GAMBAR)

        binding.backButton.setOnClickListener {
            val intentToDonation = Intent(this, DonationActivity::class.java)
            intentToDonation.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            finish()
        }

        binding.apply {
            tvDonasi.text = name
            tvDeskripsi.text = desc
            if (lokasi!!.isEmpty()) {
                tvLokasi.text = getString(R.string.lokasi_empty)
            } else tvLokasi.text = lokasi
            if (kontak!!.isEmpty()) {
                tvKontak.text = getString(R.string.kontak_empty)
            } else tvKontak.text = kontak
            if (rekening!!.isEmpty()) {
                tvRekening.text = getString(R.string.rekening_empty)
            } else tvRekening.text = rekening

            Glide.with(this@DonationDetailActivity)
                .load(gambar)
                .placeholder(R.drawable.replacement_image_preview)
                .centerCrop()
                .into(ivDonasi)
        }

        binding.buttonHome.setOnClickListener {
            AlertDialog.Builder(this@DonationDetailActivity).apply {
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

        binding.buttonWebsite.setOnClickListener {
            val intentToPayment = Intent(this, PaymentActivity::class.java)
            intentToPayment.putExtra(PaymentActivity.EXTRA_ID, id)
            startActivity(intentToPayment)
//            val urlWeb: Uri = Uri.parse(website)
//            val intentToWeb = Intent(Intent.ACTION_VIEW, urlWeb)
//            startActivity(intentToWeb)
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
        const val EXTRA_KONTAK = "extra_kontal"
        const val EXTRA_WEBSITE = "extra_website"
        const val EXTRA_NAMA = "extra_nama"
        const val EXTRA_REKENING = "extra_rekening"
        const val EXTRA_LOKASI = "extra_lokasi"
        const val EXTRA_ID = "extra_id"
        const val EXTRA_DESKRIPSI = "extra_deskripsi"
        const val EXTRA_GAMBAR = "extra_gambar"
    }
}