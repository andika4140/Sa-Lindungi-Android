package com.example.sa_lindungi.UI.donation

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sa_lindungi.UI.api.response.DonasiResponseItem
import com.example.sa_lindungi.UI.home.HomeActivity
import com.example.sa_lindungi.databinding.ActivityDonationBinding

class DonationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDonationBinding
    private lateinit var donationViewModel: DonationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDonationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        setupViewModel()
        setupAction()
    }

    private fun setupAction() {
        binding.backButton.setOnClickListener {
            val intentToHome = Intent(this, HomeActivity::class.java)
            intentToHome.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            finish()
        }
        val layoutManager = LinearLayoutManager(this)
        binding.rvDonasi.layoutManager = layoutManager
    }

    private fun setupViewModel() {
        donationViewModel = ViewModelProvider(
            this
        )[DonationViewModel::class.java]

        donationViewModel.isLoading.observe(this, {
            showLoading(it)
        })

        donationViewModel.getListDonation()
        donationViewModel.listDonation.observe(this, { donasi ->
            setListDonation(donasi)
        })
    }

    private fun setListDonation(items: List<DonasiResponseItem>) {
        val listDonation = ArrayList<DonasiResponseItem>()
        for (i in items) {
            listDonation.add(DonasiResponseItem(i.kontak, i.website, i.nama, i.rekening, i.lokasi, i.id, i.deskripsi, i.gambar, i.logo))
        }
        val listDonationAdapter = DonationAdapter(listDonation)
        binding.rvDonasi.adapter = listDonationAdapter
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.INVISIBLE
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