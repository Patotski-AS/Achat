package com.progerchat.achat.database.auth

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.progerchat.achat.model.Auth

@Database(entities = [Auth::class], version = 1, exportSchema = false)
abstract  class AuthDB: RoomDatabase() {

    abstract val authDao: AuthDAO

    companion object {

        @Volatile
        private var INSTANCE: AuthDB? = null

        fun getInstance(context: Context): AuthDB {

            synchronized(this) {

                var instance = INSTANCE

                if (instance == null) {

                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AuthDB::class.java,
                        "authDB"
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