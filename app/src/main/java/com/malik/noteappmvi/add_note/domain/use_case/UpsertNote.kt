package com.malik.noteappmvi.add_note.domain.use_case

import com.malik.noteappmvi.core.domain.model.NoteItem
import com.malik.noteappmvi.core.domain.repository.NoteRepository

class UpsertNote(
    private val noteRepository: NoteRepository
) {

    suspend operator fun invoke(
        title: String,
        description: String,
        imageUrl: String
    ): Boolean {

        if (title.isEmpty() || description.isEmpty()) {
            return false
        }

        val note = NoteItem(
            title = title,
            description = description,
            dateAdded = System.currentTimeMillis(),
            imageUrl = imageUrl
        )

        noteRepository.upsertNote(note)
        return true

    }

}