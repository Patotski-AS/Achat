package com.progerchat.achat.screens.connection

import com.progerchat.achat.databinding.AddConferenceBinding
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment


class FragmentConnection: DialogFragment() {

    private var _binding: AddConferenceBinding? = null
    private val binding get() = _binding!!

    private var listener: DialogListener? = null

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
            listener?.onAddAccount(binding.minEditText.text.toString())
            dialog?.dismiss()
        }

        binding.dismissButton.setOnClickListener {
            dialog?.dismiss()
        }

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as DialogListener
    }

    override fun onDestroyView() {
        _binding = null
        listener = null
        super.onDestroyView()
    }

    interface DialogListener {
        fun onAddAccount(e: String)
    }
}