package com.alanturing.cpifp.login.network

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthorizationInteceptor @Inject constructor(private val userDetailsHolder: UserDetailsHolder):Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val originalRequest = chain.request()
        userDetailsHolder.credentials?.let {
            val newRequest = chain.request().newBuilder()
                .header("Authorization",it)
                .build()
            return chain.proceed(newRequest)
        }
        return chain.proceed(originalRequest)
    }
}