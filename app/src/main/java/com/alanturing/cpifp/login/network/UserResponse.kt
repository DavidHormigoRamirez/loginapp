package com.alanturing.cpifp.login.network

data class UserResponse(
    val id:Long,
    val email:String,
    val name:String,
    val surname1:String,
    var surname2:String
)
