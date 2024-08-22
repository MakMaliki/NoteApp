package com.malik.noteappmvi.add_note.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.malik.noteappmvi.add_note.domain.use_case.UpsertNote
import com.malik.noteappmvi.core.data.repository.FakeNoteRepository
import kotlinx.coroutines.test.runTest
import com.google.common.truth.Truth.assertThat

import org.junit.Before
import org.junit.Rule
import org.junit.Test

class UpsertNoteTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var fakeNotesRepositoryImpl: FakeNoteRepository
    private lateinit var upsertNote: UpsertNote


    @Before
    fun setUp() {
        fakeNotesRepositoryImpl = FakeNoteRepository()
        upsertNote = UpsertNote(fakeNotesRepositoryImpl)
    }

    @Test
    fun `upsert note with empty title, return false`() = runTest {
        val isInserted = upsertNote.invoke(
            title = "",
            description = "description",
            imageUrl = "image"
        )

        assertThat(isInserted).isFalse()
    }

    @Test
    fun `upsert note with empty description, return false`() = runTest {
        val isInserted = upsertNote.invoke(
            title = "title",
            description = "",
            imageUrl = "image"
        )

        assertThat(isInserted).isFalse()
    }

    @Test
    fun `upsert a valid, return true`() = runTest {
        val isInserted = upsertNote.invoke(
            title = "title",
            description = "description",
            imageUrl = "image"
        )

        assertThat(isInserted).isTrue()
    }

}