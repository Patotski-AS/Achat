package com.progerchat.achat.screens.chat

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.progerchat.achat.databinding.ChatFragmentBinding
import com.progerchat.achat.screens.connection.FragmentConnection
import com.progerchat.achat.utils.IconHeader
import com.progerchat.achat.utils.OnListClickListener

class ChatFragment : Fragment(), OnListClickListener, FragmentConnection.DialogListener {

    private lateinit var viewModel: ChatViewModel
    private var _binding: ChatFragmentBinding? = null
    private val binding get() = _binding!!
    private var adapter: ChatAdapter? = null

    private lateinit var listener: IconHeader

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
                //
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

    override fun onAddAccount(e: String) {
        // принимаем добавленный контакт
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is IconHeader)
            listener = context
        else
            Toast.makeText(context, "Repeat please, fragment not attach", Toast.LENGTH_LONG).show()

    }

}