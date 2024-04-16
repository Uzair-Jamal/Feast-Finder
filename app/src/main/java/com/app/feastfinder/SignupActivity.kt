package com.app.feastfinder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.app.feastfinder.Model.UserModel
import com.app.feastfinder.databinding.ActivitySignupBinding
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class SignupActivity : AppCompatActivity() {

    private lateinit var userName: String
    private lateinit var password: String
    private lateinit var email: String
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference
    private lateinit var googleSignInClient: GoogleSignInClient
    private val binding:ActivitySignupBinding by lazy {
        ActivitySignupBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        // Initialize auth
        auth = Firebase.auth

        // Initialize Firebase Database
        database = Firebase.database.reference

        binding.signupBtn.setOnClickListener {
            userName = binding.name.text.toString()
            email = binding.email.text.toString()
            password = binding.password.text.toString()
            if(userName.isEmpty() || email.isEmpty() || password.isEmpty()){
                Toast.makeText(this,"Please Fill All the details",Toast.LENGTH_LONG).show()
            }else{
                createAccount(email,password)
            }

        }

        binding.haveAcc.setOnClickListener{
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }

    private fun createAccount(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener {
            task ->
            if(task.isSuccessful){
                Toast.makeText(this,"Account created Successfully",Toast.LENGTH_LONG).show()
                saveUserData()
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }else{
                Toast.makeText(this,"Account Creation Failed",Toast.LENGTH_LONG).show()
                Log.d("Account","Create Account: Failure", task.exception)
            }

        }
    }

    private fun saveUserData() {
        userName = binding.name.text.toString()
        email = binding.email.text.toString()
        password = binding.password.text.toString()

        val user = UserModel(userName,email,password)
        val userId = FirebaseAuth.getInstance().currentUser!!.uid
    }
}