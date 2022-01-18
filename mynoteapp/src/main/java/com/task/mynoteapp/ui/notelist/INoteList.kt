package com.task.mynoteapp.ui.notelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.task.core.base.interfaces.IBase
import com.task.data.local.entity.Note
import com.task.mynoteapp.ui.notelist.adapter.NoteListAdapter

interface INoteList {
    interface View : IBase.View<ViewModel> {
        fun setObserver()
    }

    interface ViewModel : IBase.ViewModel<State> {
        val noteList: LiveData<ArrayList<Note>>
        val noteListAdapter: NoteListAdapter

        fun getNoteList()
    }

    interface State : IBase.State {
        var stateLiveData: MutableLiveData<com.task.uikit.widgets.State>

    }
}