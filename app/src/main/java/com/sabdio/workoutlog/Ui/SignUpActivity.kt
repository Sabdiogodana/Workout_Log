package com.sabdio.workoutlog.Ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.sabdio.workoutlog.R
import com.sabdio.workoutlog.api.ApiClient
import com.sabdio.workoutlog.api.ApiInterface
import com.sabdio.workoutlog.databinding.ActivitySignUpBinding
import com.sabdio.workoutlog.models.RegisterRequest
import com.sabdio.workoutlog.models.RegisterResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
        binding.tvLogin.setOnClickListener {
            val intent =  Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        binding.btnSignup.setOnClickListener {
            validateSignup()
        }
    }

    fun validateSignup(){

        binding.tilName1.error = null
        binding.tilName2.error = null
        binding.tilEmail.error = null
        binding.tilPassword.error = null
        binding.tilPhoneNumber.error = null
        binding.tilConfirmPassword.error = null
        var error=false
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
        var number = binding.etPhoneNumber.text.toString()
        if (number.isBlank()){
            binding.tilPhoneNumber.error = "Email is required"
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
            val registerRequest= RegisterRequest(firstname,secondname,number, email2, password2)
            makeRegisterRequest(registerRequest)
        }
      }
    fun makeRegisterRequest(registerRequest: RegisterRequest){
        val apiClient = ApiClient.buildApiClient(ApiInterface::class.java)
        val request = apiClient.registerUser(registerRequest)

        request.enqueue(object :Callback<RegisterResponse>{
            override fun onResponse(
                call: Call<RegisterResponse>,
                response: Response<RegisterResponse>
            ) {
                if (response.isSuccessful){
                    Toast.makeText(baseContext,response.body()?.message, Toast.LENGTH_LONG).show()
//                    Navigate to login
                }
                else{
                    val error = response.errorBody()?.string()
                    Toast.makeText(baseContext, error, Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                Toast.makeText(baseContext, t.message, Toast.LENGTH_LONG).show()
            }

        })
    }
    }