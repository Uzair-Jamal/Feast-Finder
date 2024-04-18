package com.app.feastfinder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.app.feastfinder.databinding.ActivityLoginBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {
    private val binding: ActivityLoginBinding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }
    private lateinit var email: String
    private lateinit var password: String
    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase
    private lateinit var googleSignInClient: GoogleSignInClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_token)).requestEmail().build()
        // Initialization of auth
        auth = Firebase.auth
        // Initialization of database
        database = FirebaseDatabase.getInstance()
        // Initialize GoogleSignInClient
        googleSignInClient = GoogleSignIn.getClient(this,googleSignInOptions)

        binding.notAcc.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }
        binding.loginBtn.setOnClickListener {
            email = binding.emailLogin.text.toString()
            password = binding.passwordLogin.text.toString()
            if(email.isEmpty() || password.isEmpty()){
                Toast.makeText(this, "Kindly fill all the required fields", Toast.LENGTH_LONG).show()
            }
            else{
                loginAccount()
            }
        }
    }

    private fun loginAccount() {
        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener {
            task ->
            if(task.isSuccessful){
                val user = auth.currentUser
                updateUi(user)
            }
            else{
                Toast.makeText(this,"Login failed",Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun updateUi(user: FirebaseUser?) {
        Toast.makeText(this,"Login Successful",Toast.LENGTH_LONG).show()
        startActivity(Intent(this,MainActivity::class.java))
        finish()
    }
}