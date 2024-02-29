package com.app.feastfinder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.app.feastfinder.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private val binding: ActivityLoginBinding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.notAcc.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }
        binding.loginBtn.setOnClickListener {
            startActivity(Intent(this, ChooseLocationActivity::class.java))
        }
    }
}