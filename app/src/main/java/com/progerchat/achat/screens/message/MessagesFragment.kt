package com.progerchat.achat.screens.message

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.progerchat.achat.databinding.MessagesFragmentBinding
import com.progerchat.achat.model.Message


class MessagesFragment: Fragment() {

    private var _binding: MessagesFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: MessagesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _binding = MessagesFragmentBinding.inflate(inflater, container, false)

        val application = requireNotNull(this).activity?.application

        val viewModelFactory = application?.let { MessagesViewModelFactory(it) }
        viewModel = viewModelFactory?.let { ViewModelProvider(this, it) }?.get(MessagesViewModel::class.java)!!

        binding.btnSend.setOnClickListener {

            val newMessageToSend = Message(text=binding.textField.text.toString())


            if (binding.textField.text.toString() != "") {
                viewModel.addMessageConference(newMessageToSend)
            }

            binding.textField.setText("")

        }

        viewModel.selectAllMessage()

        return binding.root
    }


}