package com.alanturing.cpifp.login.network

data class CsrfTokenResponse(
    val token:String,
    val headerName:String
)
