package com.alanturing.cpifp.login.login.domain

import com.alanturing.cpifp.login.network.UserResponse

sealed class LoginResult() {
    class Success(val user: UserResponse): LoginResult()
    data object Error : LoginResult()
}
