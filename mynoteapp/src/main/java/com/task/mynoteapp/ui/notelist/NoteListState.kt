package com.task.mynoteapp.ui.notelist

import androidx.lifecycle.MutableLiveData
import com.task.core.base.BaseState
import com.task.uikit.widgets.State
import javax.inject.Inject

class NoteListState @Inject constructor() : BaseState(), INoteList.State {
    override var stateLiveData: MutableLiveData<State> = MutableLiveData()
}