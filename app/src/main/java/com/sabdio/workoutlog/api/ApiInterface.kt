package com.sabdio.workoutlog.api

import com.sabdio.workoutlog.models.LoginRequest
import com.sabdio.workoutlog.models.LoginResponse
import com.sabdio.workoutlog.models.RegisterRequest
import com.sabdio.workoutlog.models.RegisterResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {
    @POST("/register")
    fun registerUser(@Body registerRequest: RegisterRequest):Call<RegisterResponse>
    @POST("/login")
    fun loginUser(@Body loginRequest: LoginRequest): Call<LoginResponse>
}