package com.task.mynoteapp.ui.notelist.adapter

import com.task.core.base.interfaces.OnItemClickListener
import com.task.data.local.entity.Note

class NoteItemViewModel(
    val noteDate: Note,
    val position: Int,
    private val onItemClickListener: OnItemClickListener?
)