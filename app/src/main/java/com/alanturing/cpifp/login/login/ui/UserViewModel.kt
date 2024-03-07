package com.alanturing.cpifp.login.login.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alanturing.cpifp.login.login.domain.LoginResult
import com.alanturing.cpifp.login.login.domain.User
import com.alanturing.cpifp.login.network.UserNetworkRepository
import com.alanturing.cpifp.login.network.UserService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userNetworkRepository: UserNetworkRepository
):ViewModel() {
    private val _liveData:MutableLiveData<LoginResult> by lazy {
        MutableLiveData<LoginResult>()
    }
    fun login(email:String,password:String): LiveData<LoginResult>{
        viewModelScope.launch {
            val user = userNetworkRepository.login(email,password)
            _liveData.value =  if (user == null) {
                LoginResult.Error
            }
            else {
                LoginResult.Success(user)
            }
        }
        return _liveData
    }
}