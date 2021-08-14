package com.progerchat.achat.database.messages

import androidx.annotation.WorkerThread
import com.progerchat.achat.model.Message
import kotlinx.coroutines.flow.Flow

class MessageRepository(private val nameDAO: MessageDAO) {

    val allMessages: Flow<List<Message>> = nameDAO.getAllMessages()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(message: Message) {
        nameDAO.insert(message)
    }

    suspend fun deleteMessage(message: Message) {
        nameDAO.delete(message)
    }

    suspend fun clearMessageDB() {
        nameDAO.clear()
    }

    suspend fun getMessage(id: Int) {
        nameDAO.get(id)
    }

    suspend fun updateMessage(message: Message){
        nameDAO.update(message)
    }


}