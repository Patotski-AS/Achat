package com.progerchat.achat.screens.chat

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.progerchat.achat.R
import com.progerchat.achat.databinding.ChatFragmentBinding
import com.progerchat.achat.databinding.LoginFragmentBinding
import com.progerchat.achat.screens.login.LoginFragmentDirections
import com.progerchat.achat.screens.login.LoginViewModel

class ChatFragment : Fragment() {

    private lateinit var viewModel: ChatViewModel
    private var _binding: ChatFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ChatFragmentBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(ChatViewModel::class.java)


        binding.apply {
            //переход на другую активити
            button.setOnClickListener {
                view?.findNavController()
                    ?.navigate(ChatFragmentDirections.actionChatFragmentToLoginFragment())
            }
        }


        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}