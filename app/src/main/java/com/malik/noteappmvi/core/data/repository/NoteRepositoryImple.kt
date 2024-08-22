package com.malik.noteappmvi.core.data.repository

import com.malik.noteappmvi.core.data.local.NoteDao
import com.malik.noteappmvi.core.data.local.NoteDb
import com.malik.noteappmvi.core.data.mapper.toNoteEntityForDelete
import com.malik.noteappmvi.core.data.mapper.toNoteEntityForInsert
import com.malik.noteappmvi.core.data.mapper.toNoteItem
import com.malik.noteappmvi.core.domain.model.NoteItem
import com.malik.noteappmvi.core.domain.repository.NoteRepository

class NoteRepositoryImple(
    noteDb: NoteDb,
): NoteRepository {
    private val noteDao = noteDb.noteDao

    override suspend fun upsertNote(noteItem: NoteItem) {
      noteDao.upserNoteEntity(noteItem.toNoteEntityForInsert())
    }

    override suspend fun deleteNote(noteItem: NoteItem) {
        noteDao.deleteNoteEntity(noteItem.toNoteEntityForDelete())
    }

    override suspend fun getAllNoteds(): List<NoteItem> {

       return noteDao.getAllNoteEntities().map { it.toNoteItem() }
    }
}