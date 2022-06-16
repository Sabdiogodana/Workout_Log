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
import com.sabdio.workoutlog.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    lateinit var  binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        binding= ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
         castViews()
    }

    fun castViews(){
        binding.btnSignup.setOnClickListener {
            val intent = Intent(this,SignUpActivity::class.java)
            startActivity(intent)
        }
        binding.tvLogin.setOnClickListener {
            val intent =  Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }
        binding.btnSignup.setOnClickListener {
            validateSignup()
        }
    }

    fun validateSignup(){
        var error= false
        binding.tilName1.error = null
        binding.tilName2.error = null
        binding.tilEmail.error = null
        binding.tilPassword.error = null
        binding.tilConfirmPassword.error = null
        var firstname = binding.etName1.text.toString()
        if (firstname.isBlank()){
            binding.tilName1.error = "First name is required"
            error = true
        }
        var secondname = binding.etName2.text.toString()
        if (secondname.isBlank()){
            binding.tilName2.error = "Last name is required"
            error = true
        }
        var email2 = binding.etEmail2.text.toString()
        if (email2.isBlank()){
            binding.tilEmail.error = "Email is required"
            error = true

        }
        var password2 = binding.etPassword2.text.toString()
        if (password2.isBlank()){
            binding.tilPassword.error = "Password is required"
            error = true

        }
        var confirm = binding.etConfirmPassword.text.toString()
        if (confirm.isBlank()){
            binding.tilConfirmPassword.error = "Confirmation is required"
            error = true

        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email2).matches()){
            binding.tilEmail.error = "Not a valid email address"
            error=true
        }
        if(password2!=confirm) {
            binding.tilConfirmPassword.error="password does not match"

        }
        if (!error){

        }
      }
    }
