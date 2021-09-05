package com.progerchat.achat.screens.connection

import com.progerchat.achat.databinding.AddConferenceBinding
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.navigation.findNavController

class FragmentConnection: DialogFragment() {

    private var _binding: AddConferenceBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = AddConferenceBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.okButton.setOnClickListener {
            val email = binding.minEditText.text.toString()
            view.findNavController().navigate(
                FragmentConnectionDirections
                    .actionFragmentConnectionToChatFragment(email)
            )
        }

        binding.dismissButton.setOnClickListener {
            dialog?.dismiss()
        }

    }


    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }


}