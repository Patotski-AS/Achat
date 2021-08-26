package com.progerchat.achat.screens.contacts.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.progerchat.achat.databinding.ItemContactsBinding
import com.progerchat.achat.model.Contact

class ContactsAdapter (
    private val listener: ContactListener
)
    : ListAdapter<Contact, ContactViewHolder>(ContactsDiffCallback())
{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemContactsBinding.inflate(layoutInflater, parent, false)
        return ContactViewHolder(binding,listener,binding.root.context)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.bind(getItem(holder.adapterPosition))
    }
}


