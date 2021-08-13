package com.progerchat.achat.screens.login

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.progerchat.achat.R
import com.progerchat.achat.databinding.LoginFragmentBinding

class LoginFragment : Fragment() {
    private lateinit var viewModel: LoginViewModel
    private var _binding: LoginFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = LoginFragmentBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)


//        binding.apply {
//            //переход на другую активити
//            button.setOnClickListener {
//                view?.findNavController()
//                    ?.navigate(LoginFragmentDirections.actionLoginFragmentToChatFragment())
//            }
//        }



        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}