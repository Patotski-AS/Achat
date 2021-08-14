package com.progerchat.achat.database.messages

import androidx.annotation.WorkerThread
import com.progerchat.achat.model.Message
import kotlinx.coroutines.flow.Flow

class MessageRepository(private val nameDAO: MessageDAO) {

    val allMessages: Flow<List<Message>> = nameDAO.getAllMessages()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert( message: Message) {
        nameDAO.insert( message)
    }


}