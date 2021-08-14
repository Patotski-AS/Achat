package com.progerchat.achat.database.messages

import android.content.Context
import androidx.room.*
import com.progerchat.achat.model.Message
import kotlinx.coroutines.CoroutineScope

    @Database(entities = [Message::class], version = 1, exportSchema = false)
    abstract class MessageDB : RoomDatabase() {

        abstract fun messageDAO(): MessageDAO

        companion object {
            @Volatile
            private var INSTANCE: MessageDB? = null

            fun getInstance(
                context: Context,
                scope: CoroutineScope
            ): MessageDB {
                synchronized(this) {
                    var instance = INSTANCE

                    if (instance == null) {
                        instance = Room.databaseBuilder(
                            context.applicationContext,
                            MessageDB::class.java,
                            "massages"
                        )
                            .fallbackToDestructiveMigration()
                            .build()
                        INSTANCE = instance
                    }
                    return instance
                }
            }
        }
    }
