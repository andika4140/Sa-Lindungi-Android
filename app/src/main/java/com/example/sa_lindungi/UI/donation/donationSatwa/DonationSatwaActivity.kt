package com.example.sa_lindungi.UI.donation.donationSatwa

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sa_lindungi.R
import com.example.sa_lindungi.UI.api.response.DonasiResponseItem
import com.example.sa_lindungi.UI.donation.DonationAdapter
import com.example.sa_lindungi.UI.donation.DonationViewModel
import com.example.sa_lindungi.UI.home.HomeActivity
import com.example.sa_lindungi.UI.scanAnimal.result.ResultActivity
import com.example.sa_lindungi.databinding.ActivityDonationSatwaBinding

class DonationSatwaActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDonationSatwaBinding
    private lateinit var donationSatwaViewModel: DonationSatwaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDonationSatwaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        setupViewModel()
        setupAction()
    }

    private fun setupAction() {
        binding.backButton.setOnClickListener {
            val intentToResult = Intent(this, ResultActivity::class.java)
            intentToResult.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            finish()
        }

        val layoutManager = LinearLayoutManager(this)
        binding.rvDonasi.layoutManager = layoutManager
    }

    private fun setupViewModel() {
        donationSatwaViewModel = ViewModelProvider(
            this
        )[DonationSatwaViewModel::class.java]

        donationSatwaViewModel.isLoading.observe(this, {
            showLoading(it)
        })

        val id = intent.getIntExtra(EXTRA_ID, 0)

        donationSatwaViewModel.getListDonation(id)
        donationSatwaViewModel.listDonationSatwa.observe(this, { donasi ->
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

    companion object {
        const val EXTRA_ID = "extra id"
    }
}