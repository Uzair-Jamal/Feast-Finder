package com.app.feastfinder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContentProviderCompat.requireContext
import com.app.feastfinder.Fragment.CartFragment
import com.app.feastfinder.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val foodName = intent.getStringExtra("menuItemsName")
        val foodImage = intent.getIntExtra("menuItemsImage",0)

        binding.detailsFoodName.text = foodName
        binding.detailsFoodImg.setImageResource(foodImage)

        binding.detailsBackBtn.setOnClickListener {
            finish()
        }
    }
}