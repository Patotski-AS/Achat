package com.progerchat.achat.screens.contacts

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.progerchat.achat.databinding.ContactsFragmentBinding

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.progerchat.achat.database.messages.MessageDB
import com.progerchat.achat.database.messages.MessageRepository
import com.progerchat.achat.screens.contacts.adapter.ContactListener
import com.progerchat.achat.screens.contacts.adapter.ContactsAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class ContactsFragment : Fragment(), ContactListener {

    private val applicationScope = CoroutineScope(SupervisorJob())
    private val database by lazy { MessageDB.getInstance(requireActivity(), applicationScope) }
    private val repository by lazy { MessageRepository(database.messageDAO()) }

    private var _viewModel: ContactsViewModel? = null
    private val viewModel get() = _viewModel

    private var _binding: ContactsFragmentBinding? = null
    private val binding get() = _binding!!

    private val contactAdapter = ContactsAdapter(this)


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = ContactsFragmentBinding.inflate(inflater, container, false)

        val application = requireNotNull(this).activity?.application

        val viewModelFactory = application?.let { ContactFactory(repository, it) }

        binding.apply {
            recycler.adapter = contactAdapter
            recycler.layoutManager = GridLayoutManager(requireActivity(), 1)
        }
        _viewModel =
            viewModelFactory?.let { ViewModelProvider(this, it) }
                ?.get(ContactsViewModel::class.java)

        viewModel?.contacts?.observe(viewLifecycleOwner, Observer {
            contactAdapter.submitList(it)
        })

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(id: Int) {
        TODO("Not yet implemented")
    }

}