package com.malik.noteappmvi.core.domain.repository

import com.malik.noteappmvi.core.domain.model.NoteItem

interface NoteRepository {

    suspend fun upsertNote(noteItem: NoteItem)
    suspend fun deleteNote(noteItem: NoteItem)
    suspend fun getAllNoteds():List<NoteItem>
}