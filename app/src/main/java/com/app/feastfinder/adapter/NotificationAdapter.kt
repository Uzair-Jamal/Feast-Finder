package com.app.feastfinder.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.feastfinder.databinding.NotificationItemBinding

class NotificationAdapter(private var notification: ArrayList<String> , private var notificationImg: ArrayList<Int> ): RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        return NotificationViewHolder(NotificationItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
                return notification.size
            }

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        holder.notificationText.text = notification[position]
        holder.notificationImage.setImageResource(notificationImg[position])
    }

    inner class NotificationViewHolder(private val binding: NotificationItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val notificationText = binding.notificationTv
        val notificationImage = binding.notificationIv
    }
}