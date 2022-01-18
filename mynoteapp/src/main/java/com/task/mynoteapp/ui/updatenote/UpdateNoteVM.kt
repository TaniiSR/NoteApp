package com.task.mynoteapp.ui.updatenote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.task.core.base.BaseViewModel
import com.task.core.base.Dispatcher
import com.task.data.local.NoteDatabase
import com.task.data.local.entity.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UpdateNoteVM @Inject constructor(
    override val viewState: UpdateNoteState,
    private val localRepository: NoteDatabase
) : BaseViewModel<IUpdateNote.State>(), IUpdateNote.ViewModel {
    private val _updateNoteEvent: MutableLiveData<Boolean> = MutableLiveData()
    override val updateNoteEvent: LiveData<Boolean> = _updateNoteEvent
    private val _deleteNoteEvent: MutableLiveData<Boolean> = MutableLiveData()
    override val deleteNoteEvent: LiveData<Boolean> = _deleteNoteEvent
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

    override fun updateNote(note: Note) {
        launch {
            showLoading(isLoading = true)
            val update = localRepository.updateNote(note)
            launch(Dispatcher.Main) {
                _updateNoteEvent.value = update != -1
                showLoading(false, onBackGround = false)
            }
        }
    }

    override fun deleteNote(note: Note) {
        launch {
            showLoading(isLoading = true)
            val delete = localRepository.deleteNote(note)
            launch(Dispatcher.Main) {
                _updateNoteEvent.value = delete != -1
                showLoading(false, onBackGround = false)
            }
        }
    }

    override fun getUpdatedNote(): Note {
        viewState.changeNote?.noteTitle = viewState.noteTitle.get().toString()
        viewState.changeNote?.noteDescription = viewState.noteDescription.get().toString()
        viewState.changeNote?.noteImageUrl = viewState.imageUrl.get().toString()
        viewState.changeNote?.isNoteUpdated = true
        return viewState.changeNote
            ?: Note(
                noteDescription = "",
                noteTitle = "",
                noteImageUrl = "",
                noteDate = "",
                isNoteUpdated = false
            )
    }

    override fun getNote(): Note {
        return viewState.changeNote
            ?: Note(
                noteDescription = "",
                noteTitle = "",
                noteImageUrl = "",
                noteDate = "",
                isNoteUpdated = false
            )
    }


}