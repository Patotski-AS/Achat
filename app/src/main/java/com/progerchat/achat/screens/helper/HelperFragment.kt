package com.progerchat.achat.screens.helper

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.progerchat.achat.R
import com.progerchat.achat.databinding.HelperFragmentBinding
import com.progerchat.achat.databinding.PhoneFragmentBinding
import com.progerchat.achat.screens.phone.PhoneViewModel

class HelperFragment : Fragment() {

    private lateinit var viewModel: HelperViewModel
    private var _binding: HelperFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = HelperFragmentBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(HelperViewModel::class.java)



        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}