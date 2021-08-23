package com.progerchat.achat.screens.contacts

import androidx.annotation.Nullable
import androidx.recyclerview.widget.DiffUtil
import com.progerchat.achat.model.Contact

class ContactsDiffCallback :
    DiffUtil.ItemCallback<Contact>() {

    override fun areItemsTheSame(oldItem: Contact, newItem: Contact): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Contact, newItem: Contact): Boolean {
        return oldItem == newItem
    }

    @Nullable
    @Override
    override fun getChangePayload(oldItem: Contact, newItem: Contact): Any? {
        return super.getChangePayload(oldItem, newItem)
    }
}