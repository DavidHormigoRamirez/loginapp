package com.alanturing.cpifp.login.network

import okhttp3.Credentials
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserDetailsHolder @Inject constructor(){

    var token:String?   = null
    var headerName: String? =null
    private var _credentials: String? = null
    val credentials: String?
        get() = _credentials

    fun setCredentials(email:String,password:String) {
        _credentials = Credentials.basic(email,password)
    }
}