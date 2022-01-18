package com.task.noteapp.ui

import com.task.core.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashVM @Inject constructor(override val viewState: SplashState) :
    BaseViewModel<ISplash.State>(),
    ISplash.ViewModel {
}