package com.malik.noteappmvi.note_list.domain.use_case

import com.malik.noteappmvi.core.domain.model.NoteItem
import com.malik.noteappmvi.core.domain.repository.NoteRepository

class DeleteNote(
    private val noteRepository: NoteRepository
) {
    suspend operator fun invoke(note: NoteItem) {
        noteRepository.deleteNote(note)
    }

}