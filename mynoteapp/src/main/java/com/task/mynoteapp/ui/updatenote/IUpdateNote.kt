package com.task.mynoteapp.ui.updatenote

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.task.core.base.interfaces.IBase
import com.task.data.local.entity.Note

interface IUpdateNote {

    interface View : IBase.View<ViewModel> {
        fun setObserver()
        fun getArgumentsFragment()
    }

    interface ViewModel : IBase.ViewModel<State> {
        fun updateNote(note: Note)
        fun deleteNote(note: Note)
        fun getUpdatedNote(): Note
        fun getNote(): Note
        val updateNoteEvent: LiveData<Boolean>
        val deleteNoteEvent: LiveData<Boolean>

    }

    interface State : IBase.State {
        var changeNote: Note?
        val noteTitle: ObservableField<String>
        val noteDescription: ObservableField<String>
        val imageUrl: ObservableField<String>
        var isValid: MutableLiveData<Boolean>
    }
}