package com.task.noteapp.ui

import com.task.core.base.interfaces.IBase

interface ISplash {
    interface View : IBase.View<ViewModel>
    interface ViewModel : IBase.ViewModel<State>

    interface State : IBase.State
}