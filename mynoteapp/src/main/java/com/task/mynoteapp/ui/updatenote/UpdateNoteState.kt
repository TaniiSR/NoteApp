package com.task.mynoteapp.ui.updatenote

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.task.core.base.BaseState
import com.task.data.local.entity.Note
import javax.inject.Inject

class UpdateNoteState @Inject constructor() : BaseState(), IUpdateNote.State {
    override var changeNote: Note? = null
    override val noteTitle: ObservableField<String> = ObservableField("")
    override val noteDescription: ObservableField<String> = ObservableField("")
    override val imageUrl: ObservableField<String> = ObservableField("")
    override var isValid: MutableLiveData<Boolean> = MutableLiveData()
}