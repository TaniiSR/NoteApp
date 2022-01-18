package com.task.mynoteapp.ui.notelist

import com.task.data.local.NoteDatabase
import com.task.data.local.entity.Note
import com.task.mynoteapp.base.BaseTestCase
import com.task.mynoteapp.base.getOrAwaitValue
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class NoteListVMTest : BaseTestCase() {

    // Subject under test
    private lateinit var noteListVM: NoteListVM

    // Use a fake UseCase to be injected into the viewModel
    private val localRepo: NoteDatabase = mockk()

    private val viewState: NoteListState = NoteListState()

    // Mock repo response
    private lateinit var noteList: List<Note>

    @Before
    fun setUp() {
        noteList = getNoteList()
    }

    @Test
    fun `test get note list not empty`() {
        //1- Mock calls
        mainCoroutineRule.runBlockingTest {
            coEvery { localRepo.getAllNotes() } returns noteList
            //2-Call
            noteListVM = NoteListVM(viewState, localRepo)
            noteListVM.getNoteList()

            //3-verify
            Assert.assertEquals(
                true,
                noteListVM.noteList.getOrAwaitValue().isNotEmpty()
            )
        }
    }

    @Test
    fun `test get note list empty`() {
        //1- Mock calls
        mainCoroutineRule.runBlockingTest {
            coEvery { localRepo.getAllNotes() } returns listOf()
            //2-Call
            noteListVM = NoteListVM(viewState, localRepo)
            noteListVM.getNoteList()

            //3-verify
            Assert.assertEquals(
                true,
                noteListVM.noteList.getOrAwaitValue().isEmpty()
            )
        }
    }

    @Test
    fun `test get note list null or empty`() {
        //1- Mock calls
        mainCoroutineRule.runBlockingTest {
            coEvery { localRepo.getAllNotes() } returns emptyList()
            //2-Call
            noteListVM = NoteListVM(viewState, localRepo)
            noteListVM.getNoteList()

            //3-verify
            Assert.assertEquals(
                true,
                noteListVM.noteList.getOrAwaitValue().isNullOrEmpty()
            )
        }
    }


    private fun getNoteList(): List<Note> {
        val list: ArrayList<Note> = arrayListOf()
        list.add(
            Note(
                noteId = 1,
                noteTitle = "Note title",
                noteDescription = "Note Description",
                noteImageUrl = "",
                noteDate = "22/09/2021",
                isNoteUpdated = false
            )
        )
        list.add(
            Note(
                noteId = 2,
                noteTitle = "Note title",
                noteDescription = "Note Description",
                noteImageUrl = "",
                noteDate = "22/09/2021",
                isNoteUpdated = false
            )
        )
        return list
    }
}