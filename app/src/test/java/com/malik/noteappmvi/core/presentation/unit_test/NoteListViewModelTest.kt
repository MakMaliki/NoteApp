package com.malik.noteappmvi.core.presentation.unit_test

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.malik.noteappmvi.MainCoroutineRule
import com.malik.noteappmvi.core.data.repository.FakeNoteRepository
import com.malik.noteappmvi.note_list.domain.presentation.NoteListViewModel
import com.malik.noteappmvi.note_list.domain.use_case.DeleteNote
import com.malik.noteappmvi.note_list.domain.use_case.GetAllNotes
import kotlinx.coroutines.test.runTest
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NoteListViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var fakeNoteRepository: FakeNoteRepository
    private lateinit var getAllNotes: GetAllNotes
    private lateinit var deleteNote: DeleteNote

    private lateinit var noteListViewModel: NoteListViewModel

    @Before
    fun setUp() {
        fakeNoteRepository = FakeNoteRepository()
        deleteNote = DeleteNote(fakeNoteRepository)
        getAllNotes = GetAllNotes(fakeNoteRepository)

        noteListViewModel = NoteListViewModel(getAllNotes, deleteNote)
    }

    @Test
    fun `get notes from an empty list, note list is empty`() = runTest {
        fakeNoteRepository.shouldHaveFilledList(false)

        noteListViewModel.loadNotes()
        mainCoroutineRule.dispatcher.scheduler.advanceUntilIdle()

        assertThat(
            noteListViewModel.noteListState.value.isEmpty()
        ).isTrue()
    }

    @Test
    fun `get notes from a filled list, note list is not empty`() = runTest {
        fakeNoteRepository.shouldHaveFilledList(true)

        noteListViewModel.loadNotes()
        mainCoroutineRule.dispatcher.scheduler.advanceUntilIdle()

        assertThat(
            noteListViewModel.noteListState.value.isNotEmpty()
        ).isTrue()
    }

    @Test
    fun `delete note from list, note is delete`() = runTest {
        fakeNoteRepository.shouldHaveFilledList(true)

        noteListViewModel.loadNotes()
        mainCoroutineRule.dispatcher.scheduler.advanceUntilIdle()

        val note = noteListViewModel.noteListState.value[0]

        noteListViewModel.deleteNote(note)
        mainCoroutineRule.dispatcher.scheduler.advanceUntilIdle()

        assertThat(
            noteListViewModel.noteListState.value.contains(note)
        ).isFalse()
    }

    @Test
    fun `sort notes by date, notes are sorted by date`() = runTest {
        fakeNoteRepository.shouldHaveFilledList(true)

        noteListViewModel.loadNotes()
        mainCoroutineRule.dispatcher.scheduler.advanceUntilIdle()

        var notes =  noteListViewModel.noteListState.value

        for (i in 0 .. notes.size - 2) {
            assertThat(notes[i].dateAdded).isLessThan(notes[i + 1].dateAdded)
        }

        noteListViewModel.changeOrder()
        mainCoroutineRule.dispatcher.scheduler.advanceUntilIdle()

        noteListViewModel.changeOrder()
        mainCoroutineRule.dispatcher.scheduler.advanceUntilIdle()

        notes =  noteListViewModel.noteListState.value

        for (i in 0 .. notes.size - 2) {
            assertThat(notes[i].dateAdded).isLessThan(notes[i + 1].dateAdded)
        }
    }

    @Test
    fun `sort notes by title, notes are sorted by title`() = runTest {
        fakeNoteRepository.shouldHaveFilledList(true)

        noteListViewModel.changeOrder()
        mainCoroutineRule.dispatcher.scheduler.advanceUntilIdle()

        val notes =  noteListViewModel.noteListState.value

        for (i in 0 .. notes.size - 2) {
            assertThat(notes[i].title).isLessThan(notes[i + 1].title)
        }
    }
}