package com.task.mynoteapp.ui.updatenote

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.task.core.base.BaseNavFragment
import com.task.data.local.entity.Note
import com.task.mynoteapp.BR
import com.task.mynoteapp.R
import com.task.mynoteapp.databinding.FragmentUpdateNoteBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpdateNoteFragment :
    BaseNavFragment<FragmentUpdateNoteBinding, IUpdateNote.State, IUpdateNote.ViewModel>(
        R.layout.fragment_update_note
    ), IUpdateNote.View {
    override fun getBindingVariable(): Int = BR.viewModel

    override val viewModel: IUpdateNote.ViewModel by viewModels<UpdateNoteVM>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.viewState.toolbarTitle.value =
            getString(R.string.screen_update_note_toolbar_title)
        setObserver()
        getArgumentsFragment()
    }

    override fun onClick(id: Int) {
        when (id) {
            R.id.btnSave -> {
                viewModel.updateNote(viewModel.getUpdatedNote())
            }
            R.id.ivLeftIcon -> {
                requireActivity().onBackPressed()
            }
            R.id.ivRightIcon -> {
                viewModel.deleteNote(viewModel.getNote())
            }
        }
    }

    override fun setObserver() {
        viewModel.updateNoteEvent.observe(requireActivity(), {
            if (it)
                requireActivity().onBackPressed()
            else
                showToast(getString(R.string.screen_common_error))
        })
        viewModel.deleteNoteEvent.observe(requireActivity(), {
            if (it)
                requireActivity().onBackPressed()
            else
                showToast(getString(R.string.screen_common_error))
        })
    }

    override fun getArgumentsFragment() {
        arguments?.let { bundle ->
            bundle.getParcelable<Note>(
                Note::class.java.name
            )?.let {
                viewModel.viewState.changeNote = it
                viewModel.viewState.imageUrl.set(it.noteImageUrl)
                viewModel.viewState.noteTitle.set(it.noteTitle)
                viewModel.viewState.noteDescription.set(it.noteDescription)
            }
        }
    }

}