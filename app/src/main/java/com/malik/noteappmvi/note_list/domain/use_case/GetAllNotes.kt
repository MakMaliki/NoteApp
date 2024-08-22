package com.malik.noteappmvi.note_list.domain.use_case

import com.malik.noteappmvi.core.domain.model.NoteItem
import com.malik.noteappmvi.core.domain.repository.NoteRepository

class GetAllNotes(
    private val noteRepository: NoteRepository
) {

    suspend operator fun invoke(
        isOrderByTitle: Boolean
    ): List<NoteItem> {
        return if (isOrderByTitle) {
            noteRepository.getAllNoteds().sortedBy { it.title.lowercase() }
        } else {
            noteRepository.getAllNoteds().sortedBy { it.dateAdded }
        }
    }

}