package com.app.feastfinder.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.feastfinder.DetailsActivity
import com.app.feastfinder.databinding.PopularItemBinding

class PopularAdapter(private val item: List<String>, private val images: List<Int>, private val price: List<String>, private val requireContext: Context) : RecyclerView.Adapter<PopularAdapter.PopularViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularViewHolder {
        return PopularViewHolder(PopularItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }
    override fun onBindViewHolder(holder: PopularViewHolder, position: Int) {
        val popularItems = item[position]
        val popularImages = images[position]
        val popularPrice = price[position]
        holder.bind(popularItems,popularImages,popularPrice)

        holder.itemView.setOnClickListener {
            val intent = Intent(requireContext,DetailsActivity::class.java)
            intent.putExtra("menuItemsName",popularItems)
            intent.putExtra("menuItemsImage",popularImages)
            requireContext.startActivity(intent)
        }
    }
    override fun getItemCount(): Int {
        return item.size
    }
    inner class PopularViewHolder(private val binding: PopularItemBinding) : RecyclerView.ViewHolder(binding.root)  {
        fun bind(popularItems: String, popularImages: Int, popularPrice: String) {
            binding.popularFoodName.text = popularItems
            binding.popularFoodImg.setImageResource(popularImages)
            binding.popularFoodPrice.text = popularPrice
        }

    }
}