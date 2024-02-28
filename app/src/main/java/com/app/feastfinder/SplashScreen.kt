package com.app.feastfinder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.app.feastfinder.databinding.ActivitySplashScreenBinding

@Suppress("DEPRECATION", "DEPRECATION")
class SplashScreen : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Handler().postDelayed({
             val intent = Intent(this,StartActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }
}