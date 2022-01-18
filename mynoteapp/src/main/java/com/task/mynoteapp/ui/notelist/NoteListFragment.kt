package com.task.mynoteapp.ui.notelist

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import com.task.core.base.BaseNavFragment
import com.task.core.base.interfaces.OnItemClickListener
import com.task.data.local.entity.Note
import com.task.mynoteapp.BR
import com.task.mynoteapp.R
import com.task.mynoteapp.databinding.FragmentNoteListBinding
import com.task.uikit.widgets.MultiStateView
import com.task.uikit.widgets.State
import com.task.uikit.widgets.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NoteListFragment :
    BaseNavFragment<FragmentNoteListBinding, INoteList.State, INoteList.ViewModel>(
        R.layout.fragment_note_list
    ), INoteList.View {
    override fun getBindingVariable(): Int = BR.viewModel
    override val viewModel: INoteList.ViewModel by viewModels<NoteListVM>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.viewState.toolbarTitle.value = getString(R.string.screen_note_list_toolbar_title)
        setUpRecyclerView()
        setObserver()
        viewModel.getNoteList()
    }

    private fun setUpRecyclerView() {
        viewModel.viewState.stateLiveData.observe(viewLifecycleOwner, { handleState(it) })
        mViewBinding.multiStateView.setOnReloadListener(object : MultiStateView.OnReloadListener {
            override fun onReload(view: View) {
                viewModel.getNoteList()
            }
        })
        viewModel.noteListAdapter.allowFullItemClickListener = true
        viewModel.noteListAdapter.onItemClickListener = adapterClickItem
    }

    override fun setObserver() {
        viewModel.noteList.observe(requireActivity(), { noteList ->
            noteList?.let {
                viewModel.noteListAdapter.setList(it)
            } ?: showToast(getString(R.string.screen_common_error))
        })
    }

    override fun onClick(id: Int) {
        when (id) {
            R.id.fabAddBeneficiary -> {
                navigate(R.id.action_noteListFragment_to_addNoteFragment)
            }
            R.id.ivLeftIcon -> {
                requireActivity().onBackPressed()
            }
        }
    }

    private val adapterClickItem = object : OnItemClickListener {
        override fun onItemClick(view: View, data: Any, pos: Int) {
            if (data is Note) {
                // later
                navigate(
                    R.id.action_noteListFragment_to_updateNoteFragment,
                    bundleOf(Note::class.java.name to data)
                )
            }
        }
    }

    private fun handleState(state: State?) {
        when (state?.status) {
            Status.EMPTY -> {
                mViewBinding.multiStateView.viewState = MultiStateView.ViewState.EMPTY
            }
            Status.ERROR -> {
                mViewBinding.multiStateView.viewState = MultiStateView.ViewState.ERROR
            }
            Status.SUCCESS -> {
                mViewBinding.multiStateView.viewState = MultiStateView.ViewState.CONTENT
            }
            else -> throw IllegalStateException("Provided multi state is not handled $state")
        }
    }


}