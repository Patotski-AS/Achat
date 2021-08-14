package com.progerchat.achat.screens.chat

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.progerchat.achat.database.messages.MessageRepository
import com.progerchat.achat.model.Message
import kotlinx.coroutines.launch


class ChatViewModel (
    private val repository: MessageRepository,
    application: Application
) : AndroidViewModel(application) {

    val elements: LiveData<List<Message>> = repository.allMessages.asLiveData()

    fun insert(message: Message) = viewModelScope.launch {
        repository.insert(message)
    }

}