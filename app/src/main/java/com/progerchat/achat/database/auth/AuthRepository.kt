package com.progerchat.achat.database.auth

import androidx.annotation.WorkerThread
import com.progerchat.achat.model.Auth

class AuthRepository(private val nameDAO: AuthDAO) {

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(auth: Auth) {
        nameDAO.insert(auth)
    }

    suspend fun deleteAuth(auth: Auth) {
        nameDAO.delete(auth)
    }

    suspend fun getAuth(id: Int) {
        nameDAO.get(id)
    }

    suspend fun updateAuth(auth: Auth){
        nameDAO.update(auth)
    }


}