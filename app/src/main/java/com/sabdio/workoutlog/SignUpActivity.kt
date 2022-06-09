package com.sabdio.workoutlog

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class SignUpActivity : AppCompatActivity() {
    lateinit var btnSignup:Button
    lateinit var tilName1:TextInputLayout
    lateinit var etName1: TextInputEditText
    lateinit var tilName2:TextInputLayout
    lateinit var etName2: TextInputEditText
    lateinit var tilEmail:TextInputLayout
    lateinit var etEmail: TextInputEditText
    lateinit var tilPassword:TextInputLayout
    lateinit var etPassword: TextInputEditText
    lateinit var tilConfirmPassword:TextInputLayout
    lateinit var etConfirmPassword: TextInputEditText
    lateinit var tvLogin:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
         castViews()
        btnSignup.setOnClickListener {
            val intent = Intent(this,SignUpActivity::class.java)
            startActivity(intent)
        }
        tvLogin.setOnClickListener {
            val intent =  Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }
        btnSignup.setOnClickListener {
            validateSignup() }

    }

    fun castViews(){
        btnSignup = findViewById(R.id.btnSignup)
        tilName1 = findViewById(R.id.tilName1)
        etName1 = findViewById(R.id.etName1)
        tilName2 = findViewById(R.id.tilName2)
        etName2 = findViewById(R.id.etName2)
        tilEmail = findViewById(R.id.tilEmail)
        etEmail = findViewById(R.id.etEmail2)
        tilPassword = findViewById(R.id.tilPassword)
        etPassword = findViewById(R.id.etPassword2)
        tilConfirmPassword = findViewById(R.id.tilConfirmPassword)
        etConfirmPassword = findViewById(R.id.etConfirmPassword)
        tvLogin = findViewById(R.id.tvLogin)

    }
    fun validateSignup(){
        var error= false
        tilName1.error = null
        tilName2.error = null
        tilEmail.error = null
        tilPassword.error = null
        tilConfirmPassword.error = null
        var firstname = etName1.text.toString()
        if (firstname.isBlank()){
            tilName1.error = "First name is required"
            error = true
        }
        var secondname = etName2.text.toString()
        if (secondname.isBlank()){
            tilName2.error = "Last name is required"
            error = true
        }
        var email2 = etEmail.text.toString()
        if (email2.isBlank()){
            tilEmail.error = "Email is required"
            error = true

        }
        var password2 = etPassword.text.toString()
        if (password2.isBlank()){
            tilPassword.error = "Password is required"
            error = true

        }
        var confirm = etConfirmPassword.text.toString()
        if (confirm.isBlank()){
            tilConfirmPassword.error = "Confirmation is required"
            error = true

        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email2).matches()){
            tilEmail.error = "Not a valid email address"
            error=true
        }
        if(password2!=confirm) {
            tilConfirmPassword.error="password does not match"

        }
        if (!error){

        }
      }
    }
