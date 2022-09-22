package com.sabdio.workoutlog.Ui

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import com.sabdio.workoutlog.R
import com.sabdio.workoutlog.api.ApiClient
import com.sabdio.workoutlog.api.ApiInterface
import com.sabdio.workoutlog.databinding.ActivityLoginBinding
import com.sabdio.workoutlog.models.LoginRequest
import com.sabdio.workoutlog.models.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    lateinit var  binding:ActivityLoginBinding
    lateinit var sharedPrefs:SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        binding= ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        castviews()
        sharedPrefs = getSharedPreferences("WORKOUTLOG_PREFS", MODE_PRIVATE)
    }

    fun castviews(){
        binding.btnLogin.setOnClickListener{
            validateLogin()
//            startActivity(Intent(this, HomeActivity::class.java))
        }
        binding.tvSignup.setOnClickListener {
            val intent=Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }






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
            val loginRequest = LoginRequest(email,password)
            binding.pbLogin.visibility = View.VISIBLE
            makeLoginRequest(loginRequest)


        }
    }
    fun makeLoginRequest(loginRequest: LoginRequest){
        val apiClient = ApiClient.buildApiClient(ApiInterface::class.java)
        val request = apiClient.loginUser(loginRequest)

        request.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                binding.pbLogin.visibility = View.GONE
                if (response.isSuccessful){
                    val LoginResponse =response.body()
                    Toast.makeText(baseContext,LoginResponse?.message, Toast.LENGTH_LONG).show()
                    saveLoginDetails(LoginResponse!!)
                    startActivity(Intent(baseContext,HomeActivity::class.java))

//                    Navigate to login
                }
                else{
                    val error = response.errorBody()?.string()
                    Toast.makeText(baseContext, error, Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                binding.pbLogin.visibility = View.GONE
                Toast.makeText(baseContext, t.message, Toast.LENGTH_LONG).show()
            }

        })
    }
    fun saveLoginDetails(LoginResponse: LoginResponse){
        var editor = sharedPrefs.edit()
        editor.putString("ACCESS_TOKEN",LoginResponse.accesstoken)
        editor.putString("USER_ID",LoginResponse.userId)
        editor.putString("PROFILE_ID",LoginResponse.profileId)
        editor.apply()


    }
}

