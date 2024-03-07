package com.alanturing.cpifp.login.network

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserNetworkRepository @Inject constructor(
    private val userService: UserService,
    private val userDetailsHolder: UserDetailsHolder
) {
    suspend fun getCsrfToken():CsrfTokenResponse {
        val response = this.userService.getCsrfToken()
        return if (response.isSuccessful) {
            userDetailsHolder.token = response.body()!!.token
            response.body()!!
        } else {
            response.body()!!
        }
    }

    suspend fun login(email:String,password:String):UserResponse? {
        userDetailsHolder.setCredentials(email,password)
        val response = this.userService.login()
        return if (response.isSuccessful) {
            response.body()!!
        } else {
            null
        }

    }
}