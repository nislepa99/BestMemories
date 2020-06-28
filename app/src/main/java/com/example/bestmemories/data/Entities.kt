package com.example.bestmemories.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class Note(
    val date: String,
    val title: String,
    val text: String,
    @PrimaryKey(autoGenerate = true) val id: Long = 0
)