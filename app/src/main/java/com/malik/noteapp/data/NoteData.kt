package com.malik.noteapp.data

import com.malik.noteapp.model.Note

class NoteDataSource {

    fun loadNotes():List<Note>{
        return listOf(

            Note(title = "Duolingo German", description = "Reach Level 72 on Duolingo"),
            Note(title = "Android Development", description = "Reach Level 21 on Android"),
            Note(title = "Kotline Learning", description = "Reach Level 24 on Kotline"),
            Note(title = "Kotline", description = "Learn Lambda,Generic , Collections"),
            Note(title = "JetpackAndroid", description = "Finish Note App Part 1"),
            Note(title = "Compose Android", description = "Complete Note Compose App , Android"),
            Note(title = "Compose", description = "Learn some new UI development"),
            Note(title = "Kotline", description = "Learn more"),

        )
    }
}