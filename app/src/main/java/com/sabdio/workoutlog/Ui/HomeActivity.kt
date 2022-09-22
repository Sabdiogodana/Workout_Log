package com.sabdio.workoutlog.Ui

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.sabdio.workoutlog.R
import com.sabdio.workoutlog.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding
    lateinit var sharedPrefs:SharedPreferences
    lateinit var tvLoout:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    binding= ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        tvLoout = findViewById(R.id.tvLogout)
        tvLoout.setOnClickListener{
            val editor=sharedPrefs.edit()
            editor.putString("ACCESS_TOKEN","")
            editor.putString("USER_ID","")
            editor.putString("PROFILE_ID","")
            editor.apply()
            startActivity(Intent(this,LoginActivity::class.java))
            logOutRequest()

        }
        setupBottomNav()
        castView()



    }
    fun castView() {
        binding.fcvHome
        binding.bottomNavigation
    }
    fun setupBottomNav() {
        binding.bottomNavigation.setOnItemSelectedListener{ item ->
            when (item.itemId) {
                R.id.plan -> {
                    supportFragmentManager.beginTransaction() .replace(R.id.fcvHome, PlanFragment()).commit()
                    true

                }
                R.id.track -> {
                     supportFragmentManager.beginTransaction().replace(R.id.fcvHome, TrackFragment()).commit()
                    true
                }
                R.id.profile -> {
                     supportFragmentManager.beginTransaction().replace(R.id.fcvHome, ProfileFragment()).commit()
                    true
                }
                else->false
            }
        }
    }
    fun logOutRequest(){
        sharedPrefs.edit().clear().commit()
//        startActivity(Intent(this,LogInActivity::class.java))
//        finish()
    }
}