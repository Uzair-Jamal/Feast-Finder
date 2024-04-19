package com.app.feastfinder.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.feastfinder.DetailsActivity
import com.app.feastfinder.Model.MenuItem
import com.app.feastfinder.databinding.MenuItemBinding
import com.bumptech.glide.Glide

class CartMenuAdapter(
    private val menuItems: List<MenuItem>,
    private val requireContext: Context)
    : RecyclerView.Adapter<CartMenuAdapter.MenuViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
       return MenuViewHolder(MenuItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        holder.bind(position)

   }
    override fun getItemCount(): Int {
       return menuItems.size
    }

    inner class MenuViewHolder(private val binding: MenuItemBinding) :
        RecyclerView.ViewHolder(binding.root)  {
        init {
            binding.root.setOnClickListener {
                val position = adapterPosition
                if(position != RecyclerView.NO_POSITION){
                    openDetailsActivity(position)
                }

            }
        }

        private fun openDetailsActivity(position: Int) {
            val menuItem = menuItems[position]
            val intent = Intent(requireContext,DetailsActivity::class.java).apply {
                putExtra("menuItemName",menuItem.foodName)
                putExtra("menuItemImage",menuItem.foodImage)
                putExtra("menuItemDescription",menuItem.foodDescription)
                putExtra("menuItemIngredient",menuItem.foodIngredients)
                putExtra("menuItemPrice",menuItem.foodPrice)
            }
            requireContext.startActivity(intent)
        }

        fun bind(position: Int) {
            binding.menuFoodName.text = menuItems[position].foodName
            binding.menuFoodPrice.text = menuItems[position].foodPrice
            val uri = Uri.parse(menuItems[position].foodImage)
            Glide.with(requireContext).load(uri).into(binding.menuIv)
        }
    }
}


