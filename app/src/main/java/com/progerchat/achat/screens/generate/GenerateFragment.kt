package com.progerchat.achat.screens.generate

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.progerchat.achat.R
import com.progerchat.achat.databinding.GenerateFragmentBinding
import com.progerchat.achat.databinding.HelperFragmentBinding
import com.progerchat.achat.screens.helper.HelperViewModel

class GenerateFragment : Fragment() {

    private lateinit var viewModel: GenerateViewModel
    private var _binding: GenerateFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = GenerateFragmentBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(GenerateViewModel::class.java)

        binding.replaceMasterKey.setOnClickListener {

        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}