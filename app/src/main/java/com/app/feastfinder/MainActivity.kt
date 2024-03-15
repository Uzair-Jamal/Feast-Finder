package com.app.feastfinder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.app.feastfinder.Fragment.NotificationBottomFragment
import com.app.feastfinder.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val NavController = findNavController(R.id.fragmentContainerView)
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNav.setupWithNavController(NavController)

        binding.notificationBtn.setOnClickListener {
            val bottomSheetDialog = NotificationBottomFragment()
            bottomSheetDialog.show(supportFragmentManager,"Test")
        }

    }
}