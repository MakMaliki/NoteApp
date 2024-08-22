package com.malik.noteappmvi.core.data.local

import android.provider.ContactsContract.CommonDataKinds.Note
import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [NoteEntity::class],
    version = 1
)
abstract class NoteDb:RoomDatabase () {
    abstract val noteDao:NoteDao
}