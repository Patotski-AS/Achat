package com.progerchat.achat.screens.contacts.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.progerchat.achat.databinding.ItemContactsBinding
import com.progerchat.achat.model.Contact

class ContactViewHolder(
    private val binding: ItemContactsBinding,
    private val listener: ContactListener,
    private val context: Context
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(contact: Contact?) {
        binding.apply {
            textUser.text = contact?.name
        }
        initButtonsListeners(contact)
    }
    private fun initButtonsListeners(Contact: Contact?) {
    }
}