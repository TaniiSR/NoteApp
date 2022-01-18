package com.task.mynoteapp.ui.mainnote

import androidx.activity.viewModels
import com.task.core.base.BaseNavActivity
import com.task.mynoteapp.BR
import com.task.mynoteapp.R
import com.task.mynoteapp.databinding.ActivityMainNoteBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NoteMainActivity :
    BaseNavActivity<ActivityMainNoteBinding, INoteMain.State, INoteMain.ViewModel>(),
    INoteMain.View {
    override fun getLayoutId(): Int = R.layout.activity_main_note
    override fun getBindingVariable(): Int = BR.viewModel
    override val viewModel: INoteMain.ViewModel by viewModels<NoteMainVm>()
    override val navigationGraphId: Int = R.navigation.note_nav_graph
}