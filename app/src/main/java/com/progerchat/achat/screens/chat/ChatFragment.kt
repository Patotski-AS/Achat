package com.progerchat.achat.screens.chat

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.progerchat.achat.R
import com.progerchat.achat.databinding.ChatFragmentBinding
import com.progerchat.achat.databinding.LoginFragmentBinding
import com.progerchat.achat.screens.contacts.Contact
import com.progerchat.achat.screens.login.LoginFragmentDirections
import com.progerchat.achat.screens.login.LoginViewModel
import com.progerchat.achat.utils.OnListClickListener

class ChatFragment : Fragment(), OnListClickListener {

    private lateinit var viewModel: ChatViewModel
    private var _binding: ChatFragmentBinding? = null
    private val binding get() = _binding!!
    private var adapter:ChatAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ChatFragmentBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(ChatViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // add adapter
        val recyclerView = view.findViewById<RecyclerView>(R.id.list)
        recyclerView.adapter = viewModel.contacts?.value?.let { ChatAdapter(this, it.toMutableList()) }

        val swipeToDeleteCallBack = object : SwipeChat() {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                super.onSwiped(viewHolder, direction)
                val position = viewHolder.adapterPosition
                onDeleteClick(position)
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeToDeleteCallBack)
        itemTouchHelper.attachToRecyclerView(recyclerView)

        viewModel.contacts?.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter?.notifyDataSetChanged()
                // adapter?.addItems(it)
            }
        })

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDeleteClick(position: Int) {
        viewModel.deleteContact(position)
        adapter?.deleteItem(position)
    }

    override fun nextFragment(){
        view?.findNavController()
            ?.navigate(ChatFragmentDirections.actionChatFragmentToLoginFragment())
    }

}