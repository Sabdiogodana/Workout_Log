package com.sabdio.workoutlog

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class LoginActivity : AppCompatActivity() {
    lateinit var tilEmailaddress: TextInputLayout
    lateinit var etEmailaddress: TextInputEditText
    lateinit var tilPassword1: TextInputLayout
    lateinit var etPassword1: TextInputEditText
    lateinit var btnLogin: Button
    lateinit var tvSignup: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        tvSignup = findViewById(R.id.tvSignup)
        tilEmailaddress = findViewById(R.id.tilEmailaddress)
        etEmailaddress = findViewById(R.id.etEmailaddress)
        tilPassword1 = findViewById(R.id.tilPassword1)
        etPassword1 = findViewById(R.id.etPassword1)
        btnLogin = findViewById(R.id.btnLogin)
        tvSignup = findViewById(R.id.tvSignup)

        tvSignup.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        btnLogin.setOnClickListener {
                validateLogin()
        }
        }

    fun validateLogin(){
        var email = etEmailaddress.text.toString()

        if (email.isBlank()) {
            tilEmailaddress.error = "Enter email"

        }
        var password = etPassword1.text.toString()
        if (password.isBlank()) {
            tilPassword1.error = "Enter password"

        }
    }
}

