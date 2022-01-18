package com.task.mynoteapp.ui.notelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.task.core.base.BaseViewModel
import com.task.core.base.Dispatcher
import com.task.data.local.NoteDatabase
import com.task.data.local.entity.Note
import com.task.mynoteapp.ui.notelist.adapter.NoteListAdapter
import com.task.uikit.widgets.State
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NoteListVM @Inject constructor(
    override val viewState: NoteListState,
    private val localRepository: NoteDatabase
) :
    BaseViewModel<INoteList.State>(),
    INoteList.ViewModel {
    private val _noteList: MutableLiveData<ArrayList<Note>> = MutableLiveData()
    override val noteList: LiveData<ArrayList<Note>> = _noteList
    override val noteListAdapter: NoteListAdapter = NoteListAdapter(mutableListOf())

    override fun getNoteList() {
        launch {
            showLoading(isLoading = true)
            val list = localRepository.getAllNotes()
            launch(Dispatcher.Main) {
                when {
                    list.isNotEmpty() -> {
                        _noteList.value = list as ArrayList<Note>
                        viewState.stateLiveData.value = State.success()
                    }
                    list.isEmpty() -> {
                        _noteList.value = arrayListOf()
                        viewState.stateLiveData.value = State.empty()

                    }
                    else -> {
                        _noteList.value = null
                        viewState.stateLiveData.value = State.error()
                    }
                }
                showLoading(false, onBackGround = false)
            }
        }
    }

}