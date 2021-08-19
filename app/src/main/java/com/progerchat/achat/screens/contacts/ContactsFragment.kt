package com.progerchat.achat.screens.contacts

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.progerchat.achat.databinding.ContactsFragmentBinding

import android.util.Log
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.progerchat.achat.database.messages.MessageDB
import com.progerchat.achat.database.messages.MessageRepository
import com.progerchat.achat.model.Message
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class ContactsFragment : Fragment() {

    private val applicationScope = CoroutineScope(SupervisorJob())
    private val database by lazy { MessageDB.getInstance(requireActivity(), applicationScope) }
    private val repository by lazy { MessageRepository(database.messageDAO()) }

    private var _viewModel: ContactsViewModel? = null
    private val viewModel get() = _viewModel

    private var _binding: ContactsFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        _binding = ContactsFragmentBinding.inflate(inflater, container, false)

        val application = requireNotNull(this).activity?.application

        val viewModelFactory = application?.let { ContactFactory(repository, it) }
        _viewModel =
            viewModelFactory?.let { ViewModelProvider(this, it) }?.get(ContactsViewModel::class.java)

        viewModel?.elements?.observe(viewLifecycleOwner, Observer {
            Log.i("MyLog",it.toString())
        })

        binding.apply {
            //button.setOnClickListener {
              //  viewModel?.insert(Message())
            //}
        }


        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}