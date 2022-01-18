package com.task.mynoteapp.ui.mainnote

import com.task.core.base.interfaces.IBase

interface INoteMain {
    interface View : IBase.View<ViewModel>
    interface ViewModel : IBase.ViewModel<State>

    interface State : IBase.State
}