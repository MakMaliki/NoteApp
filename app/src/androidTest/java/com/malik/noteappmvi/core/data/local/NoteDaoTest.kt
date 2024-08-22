package com.malik.noteappmvi.core.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.malik.noteappmvi.core.di.AppModule
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject


@HiltAndroidTest
@SmallTest
@UninstallModules(AppModule::class)
class NoteDaoTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)


    //To Execute our Asynchrouns tasks in Main Thread
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    //Get instances NoteDb and NoteDao
    @Inject
    lateinit var noteDb: NoteDb
    private lateinit var dao: NoteDao


    @Before
    fun setUp() {
        hiltRule.inject()
        dao = noteDb.noteDao
    }

    @After
    fun tearDown() {
        noteDb.close()
    }

    @Test
    fun getAllNotesFromEmptyDb_notelistisEmpty() = runTest {

        assertThat(dao.getAllNoteEntities().isEmpty()).isTrue()

    }

    @Test
    fun getAllNotesFromEmptyDb_notelistisNotEmpty() = runTest {

        for (i in 1..5) {
            val noteEntity = NoteEntity(
                id = i,
                title = "title $i",
                description = "content $i",
                imageURL = "image $i",
                dateAdded = System.currentTimeMillis()
            )

            dao.upserNoteEntity(noteEntity)

        }


        assertThat(dao.getAllNoteEntities().isNotEmpty()).isTrue()

    }


    @Test
    fun upsertNote_noteIsUpserted() = runTest {


        val noteEntity = NoteEntity(
            id = 1,
            title = "title ",
            description = "content ",
            imageURL = "image ",
            dateAdded = System.currentTimeMillis()
        )

        dao.upserNoteEntity(noteEntity)

        assertThat(dao.getAllNoteEntities().contains(noteEntity)).isTrue()
    }

    @Test
    fun deleteNote_noteisDeleted() = runTest {


        val noteEntity = NoteEntity(
            id = 1,
            title = "title ",
            description = "content ",
            imageURL = "image ",
            dateAdded = System.currentTimeMillis()
        )

        dao.upserNoteEntity(noteEntity)

        assertThat(dao.getAllNoteEntities().contains(noteEntity)).isTrue()
        dao.deleteNoteEntity(noteEntity)
        assertThat(!dao.getAllNoteEntities().contains(noteEntity)).isTrue()

    }


}