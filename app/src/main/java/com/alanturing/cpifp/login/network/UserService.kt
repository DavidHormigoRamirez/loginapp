package com.alanturing.cpifp.login.network

import retrofit2.Response
import retrofit2.http.GET

interface UserService {
    @GET("/csrf")
    suspend fun getCsrfToken(): Response<CsrfTokenResponse>

    @GET("/login")
    suspend fun login():Response<UserResponse>
}