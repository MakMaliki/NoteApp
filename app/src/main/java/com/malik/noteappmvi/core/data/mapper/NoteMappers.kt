package com.malik.noteappmvi.core.data.mapper

import com.malik.noteappmvi.core.data.local.NoteEntity
import com.malik.noteappmvi.core.domain.model.NoteItem


//We want ID TO BE AUTO GENERATED

fun NoteItem.toNoteEntityForInsert(
): NoteEntity {
    return NoteEntity(
        title = title,
        description = description,
        imageURL = imageUrl,
        dateAdded = dateAdded
    )
}

fun NoteItem.toNoteEntityForDelete(
): NoteEntity {
    return NoteEntity(
        title = title,
        description = description,
        imageURL = imageUrl,
        dateAdded = dateAdded,
        id = id

    )
}

fun NoteEntity.toNoteItem(): NoteItem {
    return NoteItem(
        title = title,
        description = description,
        imageUrl = imageURL,
        dateAdded = dateAdded,
        id = id ?: 0
    )
}