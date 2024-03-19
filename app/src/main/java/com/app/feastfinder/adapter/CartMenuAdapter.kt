package com.app.feastfinder.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.feastfinder.DetailsActivity
import com.app.feastfinder.databinding.MenuItemBinding

class CartMenuAdapter(private val menuItems: MutableList<String>, private val menuPrice: MutableList<String>, private val menuImage: MutableList<Int>, val requireContext: Context): RecyclerView.Adapter<CartMenuAdapter.MenuViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
       return MenuViewHolder(MenuItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        val menuFoodItem = menuItems[position]
        val menuFoodPrice = menuPrice[position]
        val menuFoodImage = menuImage[position]
        holder.bind(menuFoodItem,menuFoodPrice,menuFoodImage)

        holder.itemView.setOnClickListener {
            val intent = Intent(requireContext,DetailsActivity::class.java)
            intent.putExtra("menuItemsName",menuFoodItem)
            intent.putExtra("menuItemsImage",menuFoodImage)
            requireContext.startActivity(intent)
        }
   }
    override fun getItemCount(): Int {
       return menuItems.size
    }

    inner class MenuViewHolder(private val binding: MenuItemBinding) : RecyclerView.ViewHolder(binding.root)  {
        fun bind(menuFoodItem: String, menuFoodPrice: String, menuFoodImage: Int) {
            binding.menuFoodName.text = menuFoodItem
            binding.menuFoodPrice.text = menuFoodPrice
            binding.menuIv.setImageResource(menuFoodImage)
        }

    }
}


