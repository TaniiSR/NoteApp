package com.task.mynoteapp.ui.addnote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.task.core.base.BaseViewModel
import com.task.core.base.Dispatcher
import com.task.core.utils.DateUtils.getCurrentDate
import com.task.data.local.NoteDatabase
import com.task.data.local.entity.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddNoteVM @Inject constructor(
    override val viewState: AddNoteState,
    private val localRepository: NoteDatabase
) : BaseViewModel<IAddNote.State>(), IAddNote.ViewModel {

    private val _addNoteEvent: MutableLiveData<Boolean> = MutableLiveData()
    override val addNoteEvent: LiveData<Boolean> = _addNoteEvent
    fun onTitleTextChanged(
        s: CharSequence, start: Int, before: Int,
        count: Int
    ) {
        viewState.isValid.value =
            validateNote(s.toString(), viewState.noteDescription.get() ?: "")
    }

    fun onDescriptionTextChanged(
        s: CharSequence, start: Int, before: Int,
        count: Int
    ) {
        viewState.isValid.value =
            validateNote(viewState.noteTitle.get() ?: "", s.toString())
    }

    fun validateNote(title: String, description: String): Boolean {
        return !title.isNullOrBlank() && !description.isNullOrBlank()
    }

    override fun saveNote(note: Note) {
        launch {
            showLoading(isLoading = true)
            val insert = localRepository.insertNote(note)
            launch(Dispatcher.Main) {
                _addNoteEvent.value = insert != -1L
                showLoading(false, onBackGround = false)
            }
        }
    }

    override fun getNote(): Note {
        return Note(
            noteDate = getCurrentDate() ?: "",
            noteImageUrl = viewState.imageUrl.get().toString(),
            noteDescription = viewState.noteDescription.get().toString(),
            noteTitle = viewState.noteTitle.get().toString(),
            isNoteUpdated = false
        )
    }
}