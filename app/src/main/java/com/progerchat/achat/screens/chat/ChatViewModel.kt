package com.progerchat.achat.screens.chat

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.progerchat.achat.screens.contacts.Contact

class ChatViewModel : ViewModel() {

    private var _contacts: MutableLiveData<MutableList<Contact>>? = null
    var contacts: LiveData<MutableList<Contact>>? = _contacts

    private val testAccount: MutableList<Contact> = mutableListOf(Contact("dziobadzz@gmail.com", "1"),
        Contact("dziobadzz@gmail.com", "2"), Contact("dziobadzz@gmail.com", "3"),
        Contact("dziobadzz@gmail.com", "4"), Contact("dziobadzz@gmail.com", "5"),
        Contact("dziobadzz@gmail.com", "5"), Contact("dziobadzz@gmail.com", "6"),
        Contact("dziobadzz@gmail.com", "7"))

    init {
        _contacts = MutableLiveData<MutableList<Contact>>()
        _contacts!!.value = testAccount
        contacts = _contacts
    }


    fun deleteContact(p:Int) {
        _contacts?.value?.removeAt(p)
    }

    fun addContact(a:String) {
        _contacts?.value?.add(Contact(a, ""))
    }

    fun getConference(){
        // TODO
    }


}