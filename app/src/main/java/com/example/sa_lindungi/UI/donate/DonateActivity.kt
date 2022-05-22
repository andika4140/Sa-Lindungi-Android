package com.example.sa_lindungi.UI.donate

import android.content.Intent
import android.content.Intent.EXTRA_USER
import android.text.method.TextKeyListener.clear
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import java.util.Collections.addAll

class DonateActivity: RecyclerView.Adapter<xxx.CompanyViewHolder>() {
    private val listCompany = ArrayList<xxx>()

    fun setAllData(data: List<xxx>) {
        listCompany.apply {
            clear()
            addAll(data)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompanyViewHolder {
        val view = xxx.inflate(LayoutInflater.from(parent.context), parent, false)
        return CompanyViewHolder(view)
    }

    override fun onBindViewHolder(holder: CompanyViewHolder, position: Int) {
        holder.bind(listCompany[position])
    }

    override fun getItemCount(): Int = listCompany.size

    inner class CompanyViewHolder(private val view: xxx): RecyclerView.ViewHolder(view.root) {
        fun bind(company: xxx) {
            view.apply {
                tvDonasi.text = company.donasi
            }

            Glide.with(itemView.context)
                .load(company.avatar)
                .apply(RequestOptions.circleCropTransform())
                .into(view.ivAvatar)

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, xxx::class.java)
                intent.putExtra(EXTRA_USER, company.donasi)
                itemView.context.startActivity(intent)
            }
        }
    }
}