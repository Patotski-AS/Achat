package com.progerchat.achat.screens.message


import androidx.lifecycle.ViewModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.progerchat.achat.model.Message

class MessagesViewModel(val hash: String) : ViewModel() {

    private lateinit var adapter: DatabaseReference


    fun addMessageConference(data: Message) {
        FirebaseDatabase.getInstance().reference.child(hash).child("2").push().setValue(
            data
        )
    }

    fun selectAllMessage() {
        adapter = FirebaseDatabase.getInstance().reference.child(hash).child("2")
        val a = 0
    }

}