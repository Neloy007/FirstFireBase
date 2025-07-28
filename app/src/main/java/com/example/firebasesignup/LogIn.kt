package com.example.firebasesignup

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.firebasesignup.databinding.ActivityLogInBinding
import com.google.firebase.auth.FirebaseAuth

class LogIn : AppCompatActivity() {
    private lateinit var binding:ActivityLogInBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.loginButton.setOnClickListener {
            logInUser()
        }

        binding.createAccountTv.setOnClickListener {
            startActivity(Intent(this,SignUp::class.java))
        }

    }

    private fun logInUser() {
        val email = binding.emailIdInput.text.toString().trim()
        val password = binding.passwordInputLogin.text.toString().trim()

        if (email.isEmpty() || password.isEmpty()){
            Toast.makeText(this,"Please enter Username & Password.",Toast.LENGTH_SHORT).show()
            return
        }

        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener { task ->
            if (task.isSuccessful){
                Toast.makeText(this,"Login Successfull",Toast.LENGTH_SHORT).show()
                startActivity(Intent(this,MainActivity::class.java))
                finish()
            }else {
                Toast.makeText(this,task.exception?.message?:"Failed to Login",Toast.LENGTH_SHORT).show()
            }

        }

    }


}