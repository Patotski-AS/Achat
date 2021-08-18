package com.progerchat.achat.screens.contacts

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.progerchat.achat.databinding.ContactsFragmentBinding


class ContactsFragment : Fragment() {

    private lateinit var viewModel: ContactsViewModel
    private var _binding: ContactsFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        _binding = ContactsFragmentBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(ContactsViewModel::class.java)


        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}