package com.sabdio.workoutlog

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.sabdio.workoutlog.databinding.ActivityLoginBinding
import java.util.regex.Pattern

class LoginActivity : AppCompatActivity() {
    lateinit var  binding:ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        binding= ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        castviews()
    }

    fun castviews(){
        binding.btnLogin.setOnClickListener{
            validateLogin()
            startActivity(Intent(this,HomeActivity::class.java))
        }
        binding.tvSignup.setOnClickListener {
            val intent=Intent(this,SignUpActivity::class.java)
            startActivity(intent)
        }
    }
//    tilEmailaddress = findViewById(R.id.tilEmailaddress)
//    etEmailaddress = findViewById(R.id.etEmailaddress)
//    tilPassword1 = findViewById(R.id.tilPassword1)
//    etPassword1 = findViewById(R.id.etPassword1)
//    btnLogin = findViewById(R.id.btnLogin)
//    tvSignup = findViewById(R.id.tvSignup)
//
//    tvSignup.setOnClickListener{
//        val intent = Intent(this, SignUpActivity::class.java)
//        startActivity(intent)
//    }





    fun validateLogin(){
        var error=false
        binding.tilPassword1.error= null
        binding.tilEmailaddress.error = null
        var email = binding.etEmailaddress.text.toString()

        if (email.isBlank()) {
            binding.tilEmailaddress.error = "Enter email"
            error = true

        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            binding.tilEmailaddress.error = "Not a valid email address"
            error=true
        }

        var password = binding.etPassword1.text.toString()
        if (password.isBlank()) {
            binding.tilPassword1.error = "Enter password"
            error = true

        }
        if(!error){
            startActivity(Intent(this,HomeActivity::class.java))

        }
    }
}

