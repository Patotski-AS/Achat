package com.progerchat.achat.screens.phone

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.progerchat.achat.R
import com.progerchat.achat.databinding.LoginFragmentBinding
import com.progerchat.achat.databinding.PhoneFragmentBinding
import com.progerchat.achat.screens.login.LoginViewModel

class PhoneFragment : Fragment() {

    private lateinit var viewModel: PhoneViewModel
    private var _binding: PhoneFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = PhoneFragmentBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(PhoneViewModel::class.java)



        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}