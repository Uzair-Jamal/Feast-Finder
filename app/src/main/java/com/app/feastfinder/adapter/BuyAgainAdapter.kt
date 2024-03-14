package com.app.feastfinder.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.feastfinder.databinding.BuyAgainItemBinding

class BuyAgainAdapter(private val buyAgainFoodName:ArrayList<String>,
                      private val buyAgainFoodPrice: ArrayList<String>,
                      private val buyAgainFoodImg: ArrayList<Int>): RecyclerView.Adapter<BuyAgainAdapter.BuyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BuyViewHolder {
        return BuyViewHolder(BuyAgainItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int  =  buyAgainFoodName.size

    override fun onBindViewHolder(holder: BuyViewHolder, position: Int) {
        holder.bind(buyAgainFoodName[position],buyAgainFoodPrice[position],buyAgainFoodImg[position])
    }

    class BuyViewHolder(private val binding: BuyAgainItemBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(foodName: String, foodNamePrice: String, foodNameImage: Int) {
            binding.tvBuyAgain.text = foodName
            binding.tvPriceBuyAgain.text = foodNamePrice
            binding.ivBuyAgain.setImageResource(foodNameImage)
        }

    }
}