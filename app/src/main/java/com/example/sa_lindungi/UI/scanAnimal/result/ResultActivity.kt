package com.example.sa_lindungi.UI.scanAnimal.result

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.sa_lindungi.UI.api.response.SatwaResponseItem
import com.example.sa_lindungi.UI.donation.DonationActivity
import com.example.sa_lindungi.UI.donation.donationSatwa.DonationSatwaActivity
import com.example.sa_lindungi.UI.home.HomeActivity
import com.example.sa_lindungi.UI.home.MainActivity
import com.example.sa_lindungi.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding
    private lateinit var resultViewModel: ResultViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        setupViewModel()

        binding.buttonHome.setOnClickListener { toHome() }
    }

    private fun setupViewModel() {
        resultViewModel = ViewModelProvider(
            this
        )[ResultViewModel::class.java]

        val id = intent.getIntExtra(EXTRA_ID, 0)

        binding.buttonDonasi.setOnClickListener {
            val intentToDonation = Intent(this, DonationSatwaActivity::class.java)
            intentToDonation.putExtra(DonationSatwaActivity.EXTRA_ID, id)
            startActivity(intentToDonation)
            finish()
        }

        resultViewModel.isLoading.observe(this, {
            showLoading(it)
        })

        resultViewModel.detailSatwa.observe(this, { satwa ->
            setSatwaData(satwa)
        })

        resultViewModel.getSatwaDetail(id)
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

    private fun toHome() {
        val intentToMain = Intent(this, HomeActivity::class.java)
        intentToMain.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intentToMain)
        finish()
    }

    private fun setSatwaData(satwa: SatwaResponseItem) {
        Glide.with(this)
            .load(satwa.gambar)
            .centerCrop()
            .into(binding.ivSatwa)
        Glide.with(this)
            .load(satwa.gambarLokasi)
            .into(binding.gambarPeta)
        binding.tvSatwaNameTitle.text = satwa.nama
        binding.tvNamaScientificTitle.text = satwa.namaSaintifik
        binding.tvLokasiTitle.text = satwa.lokasi
        binding.tvPopulasi.text = satwa.populasi
        binding.tvFunfact.text = satwa.funfact
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