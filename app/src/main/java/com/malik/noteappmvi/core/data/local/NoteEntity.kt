package com.malik.noteappmvi.core.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class NoteEntity(
    var title: String,
    var description: String,
    var imageURL: String,
    var dateAdded: Long,

    @PrimaryKey(autoGenerate = true)
    val id: Int? = null
)