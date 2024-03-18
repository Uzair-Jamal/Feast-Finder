package com.app.feastfinder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import com.app.feastfinder.Fragment.CartFragment
import com.app.feastfinder.Fragment.CongratsBottomSheet
import com.app.feastfinder.databinding.ActivityPayOutBinding

class PayOut : AppCompatActivity() {
    private lateinit var binding: ActivityPayOutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPayOutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.backBtn.setOnClickListener {
            finish()
        }

        binding.placeOrderBtn.setOnClickListener {
            val bottomSheet = CongratsBottomSheet()
            bottomSheet.show(supportFragmentManager,"Test")
        }
    }
}