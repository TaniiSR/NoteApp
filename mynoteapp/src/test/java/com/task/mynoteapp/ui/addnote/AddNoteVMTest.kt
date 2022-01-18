package com.task.mynoteapp.ui.addnote

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
class AddNoteVMTest : BaseTestCase() {
    // Subject under test
    private lateinit var addNoteVM: AddNoteVM

    // Use a fake UseCase to be injected into the viewModel
    private val localRepo: NoteDatabase = mockk()

    private val viewState: AddNoteState = AddNoteState()

    // Mock API response
    private lateinit var note: Note

    @Before
    fun setUp() {
        note = getMockNote()
    }

    @Test
    fun `test insert note success`() {
        viewState.noteTitle.set("Note title")
        viewState.noteDescription.set("Note Description")
        viewState.imageUrl.set("")
        addNoteVM = AddNoteVM(viewState, localRepo)
        //1- Mock calls
        mainCoroutineRule.runBlockingTest {
            coEvery { localRepo.insertNote(addNoteVM.getNote()) } returns 1
//            Mockito.`when`(DateUtils.getCurrentDate())
//                .thenReturn("22/09/2021")
            //2-Call

            addNoteVM.saveNote(addNoteVM.getNote())

            //3-verify
            Assert.assertEquals(
                true,
                addNoteVM.addNoteEvent.getOrAwaitValue()
            )
        }
    }


    @Test
    fun `test insert note failed`() {
        // Given
        viewState.noteTitle.set("Note title")
        viewState.noteDescription.set("Note Description")
        viewState.imageUrl.set("")
        addNoteVM = AddNoteVM(viewState, localRepo)
        //1- Mock calls
        mainCoroutineRule.runBlockingTest {
            coEvery { localRepo.insertNote(addNoteVM.getNote()) } returns -1
            //2-Call

            addNoteVM.saveNote(addNoteVM.getNote())

            //3-verify
            Assert.assertEquals(
                false,
                addNoteVM.addNoteEvent.getOrAwaitValue()
            )
        }
    }


    @Test
    fun `test title is empty validate`() {
        //Given
        viewState.noteDescription.set("Description")
        //when
        addNoteVM = AddNoteVM(viewState, mockk())
        addNoteVM.onTitleTextChanged("", 0, 0, 0)
        // Then
        Assert.assertEquals(false, addNoteVM.viewState.isValid.value)
    }

    @Test
    fun `test description not empty validate`() {
        //Given
        viewState.noteTitle.set("Title")
        //when
        addNoteVM = AddNoteVM(viewState, mockk())
        addNoteVM.onDescriptionTextChanged("Description", 0, 0, 0)
        // Then
        Assert.assertEquals(true, addNoteVM.viewState.isValid.value)
    }

    @Test
    fun `test description is empty validate`() {
        //Given
        viewState.noteTitle.set("Title")
        //when
        addNoteVM = AddNoteVM(viewState, mockk())
        addNoteVM.onDescriptionTextChanged("", 0, 0, 0)
        // Then
        Assert.assertEquals(false, addNoteVM.viewState.isValid.value)
    }

    private fun getMockNote(): Note {
        return Note(
            noteTitle = "Note title",
            noteDescription = "Note Description",
            noteImageUrl = "",
            noteDate = "7/09/2021",
            isNoteUpdated = false
        )
    }
}