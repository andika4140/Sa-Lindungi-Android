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

class DonateActivity: RecyclerView.Adapter<UserAdapter.UserViewHolder>() {
    private val listUser = ArrayList<User>()

    fun setAllData(data: List<User>) {
        listUser.apply {
            clear()
            addAll(data)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = ItemListUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(listUser[position])
    }

    override fun getItemCount(): Int = listUser.size

    inner class UserViewHolder(private val view: ItemListUserBinding): RecyclerView.ViewHolder(view.root) {
        fun bind(user: User) {
            view.apply {
                tvDonasi.text = user.username
            }

            Glide.with(itemView.context)
                .load(user.avatar)
                .apply(RequestOptions.circleCropTransform())
                .into(view.ivAvatar)

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DetailActivity::class.java)
                intent.putExtra(EXTRA_USER, user.username)
                itemView.context.startActivity(intent)
            }
        }
    }
}