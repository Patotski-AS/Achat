package com.progerchat.achat.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.security.SecureRandom
import javax.crypto.spec.SecretKeySpec

@Entity(tableName = "login")
data class Auth(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    @ColumnInfo(name = "initKey")
    var initKey: String = "",
    @ColumnInfo(name = "key")
    var key: String = ""
)
