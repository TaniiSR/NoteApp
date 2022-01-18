package com.task.core.base

import android.view.View
import androidx.lifecycle.*
import com.task.core.base.interfaces.IBase
import com.task.core.sealed.UIEvent
import com.task.core.base.interfaces.ILifecycle
import kotlinx.coroutines.*


abstract class BaseViewModel<S : IBase.State> : ViewModel(),
    ILifecycle, IBase.ViewModel<S> {
    override val clickEvent: SingleClickEvent = SingleClickEvent()
    fun launch(dispatcher: Dispatcher = Dispatcher.Background, block: suspend () -> Unit): Job {
        return viewModelScope.launch(
            when (dispatcher) {
                Dispatcher.Main -> Dispatchers.Main
                Dispatcher.Background -> Dispatchers.IO
                Dispatcher.LongOperation -> Dispatchers.Default
            }
        ) { block() }
    }

    fun <T> launchAsync(block: suspend () -> T): Deferred<T> =
        viewModelScope.async(Dispatchers.IO) {
            block()
        }

    fun onClick(view: View) {
        clickEvent.setValue(view.id)
    }

    override fun showLoading(isLoading: Boolean) {
        viewState.uiEvent.postValue(UIEvent.Loading(isLoading))
    }

    override fun showLoading(isLoading: Boolean, onBackGround: Boolean) {
        if (onBackGround)
            showLoading(isLoading)
        else
            viewState.uiEvent.value = UIEvent.Loading(isLoading)
    }

    override fun showToast(message: String) {
        viewState.uiEvent.postValue(UIEvent.Message(message))
    }

    override fun showToast(message: String, onBackGround: Boolean) {
        if (onBackGround)
            showToast(message)
        else
            viewState.uiEvent.value = UIEvent.Message(message)
    }

    override fun showAlertMessage(message: String) {
        viewState.uiEvent.postValue(UIEvent.Alert(message))
    }

    override fun showAlertMessage(message: String, onBackGround: Boolean) {
        if (onBackGround)
            showAlertMessage(message)
        else
            viewState.uiEvent.value = UIEvent.Alert(message)
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    override fun onCreate() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    override fun onStart() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    override fun onResume() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    override fun onPause() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    override fun onStop() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    override fun onDestroy() {
    }

    override fun registerLifecycleOwner(owner: LifecycleOwner?) {
        unregisterLifecycleOwner(owner)
        owner?.lifecycle?.addObserver(this)
    }

    override fun unregisterLifecycleOwner(owner: LifecycleOwner?) {
        owner?.lifecycle?.removeObserver(this)
    }
}