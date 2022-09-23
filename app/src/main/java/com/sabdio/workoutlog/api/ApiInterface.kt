package com.sabdio.workoutlog.api

import com.sabdio.workoutlog.models.LoginRequest
import com.sabdio.workoutlog.models.LoginResponse
import com.sabdio.workoutlog.models.RegisterRequest
import com.sabdio.workoutlog.models.RegisterResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {
    @POST("/register")
      fun registerUser(@Body registerRequest: RegisterRequest):Response<RegisterResponse>
    @POST("/login")
    suspend fun loginUser(@Body loginRequest: LoginRequest): Response<LoginResponse>
}