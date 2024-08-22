package com.malik.noteappmvi.core.domain.model

import android.icu.text.CaseMap.Title

data class NoteItem(

    var title: String,
    var description: String,
    var imageUrl: String,
    val dateAdded: Long,
    val id: Int = 0
)