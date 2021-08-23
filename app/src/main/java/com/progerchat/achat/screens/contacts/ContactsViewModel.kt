package com.progerchat.achat.screens.contacts

import android.app.Application
import androidx.lifecycle.*
import com.progerchat.achat.database.messages.MessageRepository
import com.progerchat.achat.model.Contact
import com.progerchat.achat.model.Message
import kotlinx.coroutines.launch


class ContactsViewModel (private val repository: MessageRepository, application: Application): AndroidViewModel(application) {

    private val _contacts = MutableLiveData<List<Contact>?>()
    val contacts: LiveData<List<Contact>?>
        get() = _contacts

    init {
        _contacts.value = (0..10)
            .map { Contact("name$it", "", it) }
            .toList()
    }


    val elements: LiveData<List<Message>> = repository.allMessages.asLiveData()

    fun insert(message: Message) = viewModelScope.launch {
        repository.insert(message)
    }



}