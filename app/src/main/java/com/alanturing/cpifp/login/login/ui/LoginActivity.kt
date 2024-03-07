package com.alanturing.cpifp.login.login.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alanturing.cpifp.login.R
import com.alanturing.cpifp.login.databinding.ActivityLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    //private lateinit var binding:
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}