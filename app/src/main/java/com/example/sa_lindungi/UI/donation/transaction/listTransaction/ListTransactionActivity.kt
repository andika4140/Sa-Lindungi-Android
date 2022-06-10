package com.example.sa_lindungi.UI.donation.transaction.listTransaction

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sa_lindungi.UI.api.response.TransactionListResponseItem
import com.example.sa_lindungi.databinding.ActivityListTransactionBinding

class ListTransactionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListTransactionBinding
    private lateinit var listTransactionViewModel: ListTransactionViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListTransactionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        setupViewModel()
        setupAction()

    }

    private fun setupAction() {
        val layoutManager = LinearLayoutManager(this)
        binding.rvTransaksi.layoutManager = layoutManager
    }

    private fun setupViewModel() {
        listTransactionViewModel = ViewModelProvider(
            this
        )[ListTransactionViewModel::class.java]

        val email = intent.getStringExtra(EXTRA_EMAIL)

        listTransactionViewModel.isLoading.observe(this, {
            showLoading(it)
        })

        listTransactionViewModel.listTransaction.observe(this, { transaction ->
            setListTransaction(transaction)
        })

        listTransactionViewModel.getListTransaction(email!!)
    }

    private fun setListTransaction(items: List<TransactionListResponseItem>) {
        val listTransaction = ArrayList<TransactionListResponseItem>()
        for (i in items) {
            listTransaction.add(
                TransactionListResponseItem(
                    i.donasi,
                    i.transactionStatus,
                    i.bank,
                    i.transactionTime,
                    i.transactionExpired,
                    i.id,
                    i.grossAmount,
                    i.vaNumber,
                    i.email
                )
            )
        }
        val listTransactionAdapter = ListTransactionAdapter(listTransaction)
        binding.rvTransaksi.adapter = listTransactionAdapter
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
        const val EXTRA_EMAIL = "extra_email"
    }
}