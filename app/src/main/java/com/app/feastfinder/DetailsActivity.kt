package com.app.feastfinder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import com.app.feastfinder.Fragment.CartFragment
import com.app.feastfinder.Model.CartItems
import com.app.feastfinder.databinding.ActivityDetailsBinding
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding
    private var foodName: String? = null
    private var foodPrice: String? = null
    private var foodImage: String? = null
    private var foodDescription: String? = null
    private var foodIngredients: String? = null
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        foodName = intent.getStringExtra("menuItemName")
        foodPrice = intent.getStringExtra("menuItemPrice")
        foodImage = intent.getStringExtra("menuItemImage")
        foodDescription = intent.getStringExtra("menuItemDescription")
        foodIngredients = intent.getStringExtra("menuItemIngredient")

        with(binding){
            detailsFoodName.text = foodName
            detailsDescriptionText.text = foodDescription
            detailsIngredientsText.text = foodIngredients
            Glide.with(this@DetailsActivity).load(foodImage).into(detailsFoodImg)
        }
        binding.detailsBackBtn.setOnClickListener {
            finish()
        }
        binding.detailsCartBtn.setOnClickListener {
            addItemCart()
        }

    }

    private fun addItemCart() {
        val database = FirebaseDatabase.getInstance().reference
        val userId = auth.currentUser?.uid ?: ""

        // create a cartItems objects
        val cartItem = CartItems(foodName.toString(),foodPrice.toString(),foodDescription.toString(),foodImage.toString(),1)

        // save data to Firebase
        database.child("user").child(userId).child("CartItems").push().setValue(cartItem).addOnSuccessListener {
            Toast.makeText(this,"Items added into cart Successfully",Toast.LENGTH_LONG).show()
        }.addOnFailureListener {
            Toast.makeText(this,"Items not Added",Toast.LENGTH_LONG).show()
        }

    }
}