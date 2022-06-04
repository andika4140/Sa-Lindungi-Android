package com.example.sa_lindungi.UI.scanAnimal.result

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import androidx.lifecycle.ViewModelProvider
import com.example.sa_lindungi.UI.api.response.SatwaResponseItem
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

        resultViewModel.isLoading.observe(this, {
            showLoading(it)
        })

        resultViewModel.detailSatwa.observe(this, { satwa ->
            setSatwaData(satwa)
        })

        resultViewModel.getSatwaDetail(12)
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
        binding.tvSatwaNameTitle.text = satwa.nama
        binding.namaLatinSatwa.text = "Nama scientific satwa ini adalah ${satwa.namaSaintifik}"
        binding.lokasiSatwa.text = satwa.lokasi
        binding.populasiSatwa.text = satwa.populasi
        binding.funfactSatwa.text = satwa.funfact
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.INVISIBLE
        }
    }
}