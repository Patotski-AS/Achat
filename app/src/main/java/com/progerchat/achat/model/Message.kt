package com.progerchat.achat.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "messages")
data class Message(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @ColumnInfo(name = "text") var text: String? = "",
    @ColumnInfo(name = "time") var time: Long? = System.currentTimeMillis()
)