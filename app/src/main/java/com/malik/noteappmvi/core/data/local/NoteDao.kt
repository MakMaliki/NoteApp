package com.malik.noteappmvi.core.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface NoteDao {

    @Upsert
    suspend fun upserNoteEntity(noteEntity: NoteEntity)

    @Query("SELECT * FROM noteentity")
    suspend fun getAllNoteEntities():List<NoteEntity>

    @Delete
    suspend fun deleteNoteEntity(noteEntity: NoteEntity)

}