package com.progerchat.achat.database.auth

import androidx.room.*
import com.progerchat.achat.model.Auth

@Dao
interface AuthDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg auth: Auth)

    @Update
    suspend fun update(auth: Auth)

    @Query("SELECT * from login WHERE id = :key")
    suspend fun get(key: Int): Auth?

    @Delete
    suspend fun delete(auth: Auth)

}