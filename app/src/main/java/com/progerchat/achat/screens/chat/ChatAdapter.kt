package com.progerchat.achat.screens.chat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.progerchat.achat.databinding.ItemContactsBinding
import com.progerchat.achat.model.Contact
import com.progerchat.achat.screens.contacts.adapter.ContactsDiffCallback
import com.progerchat.achat.utils.OnListClickListener


class ChatAdapter(private val listener: OnListClickListener,
                  private val contacts: MutableList<Contact>) :
    ListAdapter<Contact, ChatAdapter.ViewHolder>(ContactsDiffCallback())  {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemContactsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.vBinding.textUser.text = contacts[position].name
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemId(position: Int): Long = position.toLong()


    override fun getItemCount() = contacts.size

    inner class ViewHolder(val vBinding: ItemContactsBinding) :

        RecyclerView.ViewHolder(vBinding.root) {

        init {

            vBinding.imageUser.setOnClickListener{
                listener.nextFragment()
            }

            vBinding.imageGenerate.setOnClickListener {
                listener.replaceKeyContact()
            }

        }

    }


    fun addItems(list:List<Contact>) {
        contacts.addAll(list.toList())
    }

    fun deleteItem(p:Int) {
        contacts.removeAt(p)
        notifyDataSetChanged()
    }



}