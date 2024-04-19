package com.app.feastfinder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContentProviderCompat.requireContext
import com.app.feastfinder.Fragment.CartFragment
import com.app.feastfinder.databinding.ActivityDetailsBinding
import com.bumptech.glide.Glide

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding
    private var foodName: String? = null
    private var foodPrice: String? = null
    private var foodImage: String? = null
    private var foodDescription: String? = null
    private var foodIngredients: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        foodName = intent.getStringExtra("menuItemName")
        foodPrice = intent.getStringExtra("menuItemPrice")
        foodImage = intent.getStringExtra("menuItemImage")
        foodDescription = intent.getStringExtra("menuItemDescription")
        foodIngredients = intent.getStringExtra("menuItemIngredient")

        binding.apply {
            detailsFoodName.text = foodName
            detailsDescriptionText.text = foodDescription
            detailsIngredientsText.text = foodIngredients
            Glide.with(this@DetailsActivity).load(foodImage).into(detailsFoodImg)
        }

        binding.detailsBackBtn.setOnClickListener {
            finish()
        }
    }
}