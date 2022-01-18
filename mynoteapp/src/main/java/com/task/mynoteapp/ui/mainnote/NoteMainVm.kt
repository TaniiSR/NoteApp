package com.task.mynoteapp.ui.mainnote

import com.task.core.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NoteMainVm @Inject constructor(override val viewState: NoteMainState) :
    BaseViewModel<INoteMain.State>(),
    INoteMain.ViewModel {
}