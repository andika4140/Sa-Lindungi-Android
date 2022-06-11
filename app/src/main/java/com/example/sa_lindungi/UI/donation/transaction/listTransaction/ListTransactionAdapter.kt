package com.example.sa_lindungi.UI.donation.transaction.listTransaction

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sa_lindungi.UI.api.response.TransactionListResponseItem
import com.example.sa_lindungi.UI.donation.transaction.status.DonationStatusActivity
import com.example.sa_lindungi.databinding.ItemTransactionBinding

class ListTransactionAdapter(private val listTransaction: ArrayList<TransactionListResponseItem>) :
    RecyclerView.Adapter<ListTransactionAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemTransactionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val transaction = listTransaction[position]
        holder.bind(transaction)
    }

    override fun getItemCount(): Int = listTransaction.size

    inner class ListViewHolder(private val binding: ItemTransactionBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun bind(data: TransactionListResponseItem) {
                binding.apply {
                    tvDonasi.text = data.donasi
                    tvTglTransaksi.text = data.transactionTime
                    tvStatus.text = "Status: ${data.transactionStatus}"
                    tvNominal.text = "Rp${data.grossAmount}"
                    cardView.setOnClickListener {
                        val intentToStatus = Intent(itemView.context, DonationStatusActivity::class.java)
                        intentToStatus.putExtra(DonationStatusActivity.EXTRA_ID, data.id)
                        itemView.context.startActivity(intentToStatus)
                    }
                }
            }
        }
}