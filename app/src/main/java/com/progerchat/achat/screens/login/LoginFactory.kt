package com.progerchat.achat.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.progerchat.achat.database.auth.AuthDAO
import com.progerchat.achat.screens.message.MessagesViewModel

class LoginFactory(private val authDB: AuthDAO): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MessagesViewModel::class.java)) {
            return LoginFactory(authDB) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}