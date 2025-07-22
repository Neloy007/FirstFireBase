package com.example.firebasesignup

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.firebasesignup.databinding.ActivitySignUpBinding
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth

class SignUp : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.signInButton.setOnClickListener {
            signUpUser()
        }

        binding.alreadyAccountTv.setOnClickListener {
            startActivity(Intent(this,LogIn::class.java))
        }

    }

    private fun signUpUser() {
        val userName = binding.usernameInput.text.toString().trim()
        val email = binding.emailIdInput.text.toString().trim()
        val phone = binding.phoneNoInput.text.toString().trim()
        val password = binding.passwordInput.text.toString().trim()
        val confirmPass = binding.confirmPasswordInput.text.toString().trim()

        if (userName.isEmpty()||email.isEmpty()||phone.isEmpty()||password.isEmpty()||confirmPass.isEmpty()){
            Toast.makeText(this,"Please fill all the fields.",Toast.LENGTH_SHORT).show()
            return
        }
        if (password != confirmPass){
            Toast.makeText(this,"Please enter the same password.",Toast.LENGTH_SHORT).show()
            return
        }

        firebaseAuth.createUserWithEmailAndPassword(userName,password).addOnCompleteListener { task ->
            if (task.isSuccessful){
                Toast.makeText(this,"Account Created Successfully",Toast.LENGTH_SHORT).show()
                startActivity(Intent(this,MainActivity::class.java))
                finish()
            }else {
                Toast.makeText(this,task.exception?.message?:"Sign UP Failed",Toast.LENGTH_SHORT).show()
            }

        }
    }
}