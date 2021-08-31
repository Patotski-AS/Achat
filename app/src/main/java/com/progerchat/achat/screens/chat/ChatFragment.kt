package com.progerchat.achat.screens.chat

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.progerchat.achat.databinding.ChatFragmentBinding
import com.progerchat.achat.utils.OnListClickListener

class ChatFragment : Fragment(), OnListClickListener {

    private lateinit var viewModel: ChatViewModel
    private var _binding: ChatFragmentBinding? = null
    private val binding get() = _binding!!
    private var adapter: ChatAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ChatFragmentBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(ChatViewModel::class.java)

        adapter = viewModel.contacts?.value?.let {
            ChatAdapter(this, it.toMutableList())
        }

        binding.list.adapter = adapter

        binding.floatingActionButton.setOnClickListener {
            view?.findNavController()
                ?.navigate(ChatFragmentDirections.actionChatFragmentToFragmentConnection())
        }

        val swipeToDeleteCallBack = object : SwipeChat() {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                super.onSwiped(viewHolder, direction)
                val position = viewHolder.adapterPosition
                onDeleteClick(position)
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeToDeleteCallBack)
        itemTouchHelper.attachToRecyclerView(binding.list)

        return binding.root
    }

    private fun onAddAccount() {
        // принимаем добавленный контакт
        val email = ChatFragmentArgs.fromBundle(requireArguments()).email
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDeleteClick(position: Int) {
        viewModel.deleteContact(position)
        adapter?.deleteItem(position)
    }

    override fun nextFragment() {
        view?.findNavController()
            ?.navigate(ChatFragmentDirections.actionChatFragmentToLoginFragment())
    }



}