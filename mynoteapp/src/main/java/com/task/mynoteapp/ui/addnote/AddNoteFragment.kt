package com.task.mynoteapp.ui.addnote

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.task.core.base.BaseNavFragment
import com.task.mynoteapp.BR
import com.task.mynoteapp.R
import com.task.mynoteapp.databinding.FragmentAddNoteBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddNoteFragment :
    BaseNavFragment<FragmentAddNoteBinding, IAddNote.State, IAddNote.ViewModel>(
        R.layout.fragment_add_note
    ), IAddNote.View {
    override fun getBindingVariable(): Int = BR.viewModel

    override val viewModel: IAddNote.ViewModel by viewModels<AddNoteVM>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.viewState.toolbarTitle.value = getString(R.string.screen_add_note_toolbar_title)

        setObserver()
    }

    override fun onClick(id: Int) {
        when (id) {
            R.id.btnSave -> {
                viewModel.saveNote(viewModel.getNote())
            }
            R.id.ivLeftIcon -> {
                requireActivity().onBackPressed()
            }
        }
    }

    override fun setObserver() {
        viewModel.addNoteEvent.observe(requireActivity(), {
            if (it)
                requireActivity().onBackPressed()
            else
                showToast(getString(R.string.screen_common_error))
        })
    }
}