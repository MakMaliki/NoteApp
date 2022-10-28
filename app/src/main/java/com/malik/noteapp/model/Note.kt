package com.malik.noteapp.model

import android.icu.text.CaseMap
import java.time.LocalDateTime
import java.util.*

data class Note(
    val id: UUID = UUID.randomUUID(),//Random ID created
    val title: String,
    val description: String,
    val entryDate: LocalDateTime = LocalDateTime.now()
)
