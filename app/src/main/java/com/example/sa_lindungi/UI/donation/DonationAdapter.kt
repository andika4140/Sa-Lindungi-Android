package com.example.sa_lindungi.UI.donation

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sa_lindungi.R
import com.example.sa_lindungi.UI.api.response.DonasiResponseItem
import com.example.sa_lindungi.UI.donation.donationDetail.DonationDetailActivity
import com.example.sa_lindungi.databinding.ItemDonateBinding

class DonationAdapter(private val listDonation: ArrayList<DonasiResponseItem>) :
    RecyclerView.Adapter<DonationAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemDonateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val donasi = listDonation[position]
        if (donasi != null) {
            holder.bind(donasi)
        }
    }

    override fun getItemCount(): Int = listDonation.size

    inner class ListViewHolder(private val binding: ItemDonateBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun bind(data: DonasiResponseItem) {
                Glide.with(itemView)
                    .load(data.logo)
                    .placeholder(R.drawable.replacement_image_preview)
                    .into(binding.ivAvatar)
                binding.tvDonasi.text = data.nama
                binding.cardView.setOnClickListener {
                    val intentToDetail = Intent(itemView.context, DonationDetailActivity::class.java)
                    intentToDetail.putExtra(DonationDetailActivity.EXTRA_ID, data.id)
                    intentToDetail.putExtra(DonationDetailActivity.EXTRA_NAMA, data.nama)
                    intentToDetail.putExtra(DonationDetailActivity.EXTRA_DESKRIPSI,data.deskripsi)
                    intentToDetail.putExtra(DonationDetailActivity.EXTRA_KONTAK, data.kontak)
                    intentToDetail.putExtra(DonationDetailActivity.EXTRA_LOKASI, data.lokasi)
                    intentToDetail.putExtra(DonationDetailActivity.EXTRA_REKENING, data.rekening)
                    intentToDetail.putExtra(DonationDetailActivity.EXTRA_WEBSITE, data.website)
                    intentToDetail.putExtra(DonationDetailActivity.EXTRA_GAMBAR, data.gambar)
                    itemView.context.startActivity(intentToDetail)
                }
            }
    }
}