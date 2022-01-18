package com.task.mynoteapp.ui.addnote

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.task.core.base.interfaces.IBase
import com.task.data.local.entity.Note

interface IAddNote {
    interface View : IBase.View<ViewModel> {
        fun setObserver()
    }

    interface ViewModel : IBase.ViewModel<State> {
        fun saveNote(note: Note)
        fun getNote(): Note
        val addNoteEvent: LiveData<Boolean>
    }

    interface State : IBase.State {
        val noteTitle: ObservableField<String>
        val noteDescription: ObservableField<String>
        val imageUrl: ObservableField<String>
        var isValid: MutableLiveData<Boolean>
    }
}