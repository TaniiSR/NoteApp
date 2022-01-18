package com.task.mynoteapp.ui.updatenote

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
class UpdateNoteVMTest : BaseTestCase() {

    // Subject under test
    private lateinit var updateNoteVM: UpdateNoteVM

    // Use a fake UseCase to be injected into the viewModel
    private val localRepo: NoteDatabase = mockk()

    private val viewState: UpdateNoteState = UpdateNoteState()

    // Mock API response
    private lateinit var note: Note

    @Before
    fun setUp() {
        note = getMockNote()
    }

    @Test
    fun `test update note success`() {
        viewState.changeNote = note
        viewState.noteTitle.set("Title 2")
        viewState.noteDescription.set("Description")
        viewState.imageUrl.set("")
        //1- Mock calls
        mainCoroutineRule.runBlockingTest {
            coEvery { localRepo.updateNote(note) } returns 1
            //2-Call
            updateNoteVM = UpdateNoteVM(viewState, localRepo)
            updateNoteVM.updateNote(updateNoteVM.getUpdatedNote())

            //3-verify
            Assert.assertEquals(
                true,
                updateNoteVM.updateNoteEvent.getOrAwaitValue()
            )
        }
    }

    @Test
    fun `test update note failed`() {
        viewState.changeNote = note
        viewState.noteTitle.set("Title 2")
        viewState.noteDescription.set("Description")
        viewState.imageUrl.set("")
        //1- Mock calls
        mainCoroutineRule.runBlockingTest {
            coEvery { localRepo.updateNote(note) } returns -1
            //2-Call
            updateNoteVM = UpdateNoteVM(viewState, localRepo)
            updateNoteVM.updateNote(updateNoteVM.getUpdatedNote())

            //3-verify
            Assert.assertEquals(
                false,
                updateNoteVM.updateNoteEvent.getOrAwaitValue()
            )
        }
    }

    @Test
    fun `test delete note failed`() {
        viewState.changeNote = note
        //1- Mock calls
        mainCoroutineRule.runBlockingTest {
            coEvery { localRepo.deleteNote(note) } returns -1
            //2-Call
            updateNoteVM = UpdateNoteVM(viewState, localRepo)
            updateNoteVM.deleteNote(updateNoteVM.getNote())

            //3-verify
            Assert.assertEquals(
                false,
                updateNoteVM.updateNoteEvent.getOrAwaitValue()
            )
        }
    }

    @Test
    fun `test delete note success`() {
        viewState.changeNote = note
        //1- Mock calls
        mainCoroutineRule.runBlockingTest {
            coEvery { localRepo.deleteNote(note) } returns 1
            //2-Call
            updateNoteVM = UpdateNoteVM(viewState, localRepo)
            updateNoteVM.deleteNote(updateNoteVM.getNote())

            //3-verify
            Assert.assertEquals(
                true,
                updateNoteVM.updateNoteEvent.getOrAwaitValue()
            )
        }
    }

    @Test
    fun `test title not empty validate`() {
        //Given
        viewState.noteDescription.set("Description")
        //when
        updateNoteVM = UpdateNoteVM(viewState, mockk())
        updateNoteVM.onTitleTextChanged("Title", 0, 0, 0)
        // Then
        Assert.assertEquals(true, updateNoteVM.viewState.isValid.value)
    }

    @Test
    fun `test title is empty validate`() {
        //Given
        viewState.noteDescription.set("Description")
        //when
        updateNoteVM = UpdateNoteVM(viewState, mockk())
        updateNoteVM.onTitleTextChanged("", 0, 0, 0)
        // Then
        Assert.assertEquals(false, updateNoteVM.viewState.isValid.value)
    }

    @Test
    fun `test description not empty validate`() {
        //Given
        viewState.noteTitle.set("Title")
        //when
        updateNoteVM = UpdateNoteVM(viewState, mockk())
        updateNoteVM.onDescriptionTextChanged("Description", 0, 0, 0)
        // Then
        Assert.assertEquals(true, updateNoteVM.viewState.isValid.value)
    }

    @Test
    fun `test description is empty validate`() {
        //Given
        viewState.noteTitle.set("Title")
        //when
        updateNoteVM = UpdateNoteVM(viewState, mockk())
        updateNoteVM.onDescriptionTextChanged("", 0, 0, 0)
        // Then
        Assert.assertEquals(false, updateNoteVM.viewState.isValid.value)
    }


    private fun getMockNote(): Note {
        return Note(
            noteId = 1,
            noteTitle = "Note title",
            noteDescription = "Note Description",
            noteImageUrl = "",
            noteDate = "22/09/2021",
            isNoteUpdated = false
        )
    }
}