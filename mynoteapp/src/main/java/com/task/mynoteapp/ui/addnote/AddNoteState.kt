package com.task.mynoteapp.ui.addnote

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.task.core.base.BaseState
import javax.inject.Inject

class AddNoteState @Inject constructor() : BaseState(), IAddNote.State {
    override val noteTitle: ObservableField<String> = ObservableField("")
    override val noteDescription: ObservableField<String> = ObservableField("")
    override val imageUrl: ObservableField<String> = ObservableField("")
    override var isValid: MutableLiveData<Boolean> = MutableLiveData()
}