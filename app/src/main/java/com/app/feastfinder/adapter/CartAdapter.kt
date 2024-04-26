package com.app.feastfinder.adapter

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.app.feastfinder.databinding.CartItemBinding
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class CartAdapter(
    private val context: Context,
    private val cartItems: MutableList<String>,
    private val cartItemImages: MutableList<String>,
    private val cartItemPrice: MutableList<String>,
    private var cartDescription: MutableList<String>,
    private val cartQuantity: MutableList<Int>,
    private val cartIngredient: MutableList<String>


    ) : RecyclerView.Adapter<CartAdapter.CartViewHolder>()  {

    private val auth = FirebaseAuth.getInstance()

    init{
        val database = FirebaseDatabase.getInstance()
        val userId = auth.currentUser?.uid?:""
        val cartItemNumber = cartItems.size

        itemQuantities = IntArray(cartItemNumber){1}
        cartItemsReference = database.reference.child("user").child(userId).child("cartItems")
    }
    companion object{
        private var itemQuantities: IntArray = intArrayOf()
        private lateinit var cartItemsReference: DatabaseReference
    }
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
        fun bind(cartItem: String, cartImages: String, cartPrice: String) {
            binding.itemCartName.text = cartItem
            val uriString: String = cartImages
            val uri: Uri = Uri.parse(uriString)

            // Load images
            Glide.with(context).load(uri).into(binding.cartImg)

            binding.cartItemPrice.text = cartPrice
            val quantity = itemQuantities[position]
            binding.cartQuantity.text = quantity.toString()

            binding.plusBtn.setOnClickListener {
                if(itemQuantities[position] in 0..9){
                    itemQuantities[position]++
                    binding.cartQuantity.text = itemQuantities[position].toString()
                }
            }
            binding.minusBtn.setOnClickListener {
                if(itemQuantities[position] in 1..10){
                    itemQuantities[position]--
                    binding.cartQuantity.text = itemQuantities[position].toString()
                }
            }
            binding.deleteCart.setOnClickListener {
                val positionRetrieve = position
                getUniqueKeyAtPosition(positionRetrieve){uniqueKey ->
                    if(uniqueKey != null){
                        removeItem(position,uniqueKey)
                    }
                }

            }
        }

        private fun removeItem(position: Int, uniqueKey: String) {
            cartItemsReference.child(uniqueKey).removeValue().addOnSuccessListener {
                cartItems.removeAt(position)
                cartItemImages.removeAt(position)
                cartDescription.removeAt(position)
                cartQuantity.removeAt(position)
                cartItemPrice.removeAt(position)
                cartIngredient.removeAt(position)
                Toast.makeText(context,"Items Deleted",Toast.LENGTH_LONG).show()
                itemQuantities = itemQuantities.filterIndexed { index, i -> index != position }.toIntArray()
                notifyItemRemoved(position)
                notifyItemRangeChanged(position,cartItems.size)
            }.addOnFailureListener {
                Toast.makeText(context,"Failed to Delete",Toast.LENGTH_LONG).show()
            }

        }

        private fun getUniqueKeyAtPosition(positionRetrieve: Int, onComplete:(String) -> Unit) {
            cartItemsReference.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    var uniqueKey:String? = null

                    snapshot.children.forEachIndexed { index, dataSnapshot ->
                        if(index == positionRetrieve){
                            uniqueKey = dataSnapshot.key
                            return@forEachIndexed
                        }
                    }
                    onComplete(uniqueKey!!)
                }

                override fun onCancelled(error: DatabaseError) {

                }

            })
        }

    }
}