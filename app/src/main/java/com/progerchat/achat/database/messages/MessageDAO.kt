package com.progerchat.achat.database.messages

import androidx.room.*
import com.progerchat.achat.model.Message
import kotlinx.coroutines.flow.Flow


@Dao
interface MessageDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg message: Message)

    @Update
    suspend fun update(message: Message)

    @Query("SELECT * from messages WHERE id = :key")
    suspend fun get(key: Int): Message?

    @Delete
    suspend fun delete(message: Message)

    @Query("DELETE FROM messages")
    suspend fun clear()

    @Query("SELECT * FROM messages")
    fun getAllMessages(): Flow<List<Message>>

}