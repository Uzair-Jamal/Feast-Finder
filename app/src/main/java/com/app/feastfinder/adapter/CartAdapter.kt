package com.app.feastfinder.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.feastfinder.databinding.CartItemBinding

class CartAdapter(private val cartItems: MutableList<String>, private val cartItemImages: MutableList<Int>,private val cartItemPrice: MutableList<String>) : RecyclerView.Adapter<CartAdapter.CartViewHolder>()  {

    private val itemQuantities = IntArray(cartItems.size){1}
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartAdapter.CartViewHolder {
        return CartViewHolder(CartItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: CartAdapter.CartViewHolder, position: Int) {
        val cartItem = cartItems[position]
        val cartImages = cartItemImages[position]
        val cartPrice = cartItemPrice[position]
        holder.bind(cartItem,cartImages,cartPrice)

    }

    override fun getItemCount(): Int {
        return cartItems.size
    }

    inner class CartViewHolder(private val binding : CartItemBinding ): RecyclerView.ViewHolder(binding.root) {
        fun bind(cartItem: String, cartImages: Int, cartPrice: String) {
            binding.itemCartName.text = cartItem
            binding.cartImg.setImageResource(cartImages)
            binding.cartItemPrice.text = cartPrice
            val quantity = itemQuantities[position]
            binding.cartQuantity.text = quantity.toString()

            binding.plusBtn.setOnClickListener {
                if(itemQuantities[position] > 1){
                    itemQuantities[position]++
                    binding.cartQuantity.text = itemQuantities[position].toString()
                }
            }
            binding.minusBtn.setOnClickListener {
                if(itemQuantities[position]<=10){
                    itemQuantities[position]--
                    binding.cartQuantity.text = itemQuantities[position].toString()
                }
            }
            binding.deleteCart.setOnClickListener {
                cartItems.removeAt(position)
                cartItemImages.removeAt(position)
                cartItemPrice.removeAt(position)
                notifyItemRemoved(position)
                notifyItemRangeChanged(position,cartItems.size)
            }


        }

    }

    private fun deleteCart() {
        TODO("Not yet implemented")
    }

    private fun decreaseCart() {
        TODO("Not yet implemented")
    }

    private fun increaseCart(position:Int) {
        if(itemQuantities[position]>1){
            itemQuantities[position]++

        }
    }
}