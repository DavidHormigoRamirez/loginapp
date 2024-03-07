package com.alanturing.cpifp.login.network

import dagger.Provides
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class CsrfInterceptor @Inject constructor(private val userDetailsHolder: UserDetailsHolder):Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val request =  when (originalRequest.method()) {
            "PUT","POST","DELETE" -> {
                userDetailsHolder.token?.let {
                    chain.request().newBuilder().header(userDetailsHolder.headerName,it).build() }
            }
            else -> {
                originalRequest
            }
        }
        return chain.proceed(request)
    }
}