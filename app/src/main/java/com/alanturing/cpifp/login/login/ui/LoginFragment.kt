package com.alanturing.cpifp.login.login.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.alanturing.cpifp.login.MainActivity
import com.alanturing.cpifp.login.databinding.FragmentLoginBinding
import com.alanturing.cpifp.login.login.domain.LoginResult
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private val userViewModel: UserViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater,
            container,
            false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val userObserver = Observer<LoginResult> { result ->
            when(result) {
                is LoginResult.Success -> {
                    val intent = Intent(context,MainActivity::class.java)
                    startActivity(intent)
                    activity?.finish()
                }
                LoginResult.Error -> {
                    Toast.makeText(context,"Usuario o contraseña incorrecto",Toast.LENGTH_LONG).show()
                }
            }

        }
        binding.loginButton.setOnClickListener {
            userViewModel.login(
                binding.userFieldInput.text.toString(),
                binding.passwordFieldInput.text.toString())
                .observe(viewLifecycleOwner,userObserver
                )
        }

        binding.registerButton.setOnClickListener {
            val action =  LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
            it.findNavController().navigate(action)
        }
    }
}