package com.task.mynoteapp.ui.notelist.adapter

import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.task.core.base.BaseBindingRecyclerAdapter
import com.task.core.base.interfaces.OnItemClickListener
import com.task.data.local.entity.Note
import com.task.mynoteapp.R
import com.task.mynoteapp.databinding.LayoutNoteListItemBinding

class NoteListAdapter(
    private val list: MutableList<Note>
) : BaseBindingRecyclerAdapter<Note, NoteListAdapter.NoteListItemViewHolder>(
    list
) {


    override fun onCreateViewHolder(binding: ViewDataBinding): NoteListItemViewHolder {
        return NoteListItemViewHolder(binding as LayoutNoteListItemBinding)
    }

    override fun getLayoutIdForViewType(viewType: Int): Int = R.layout.layout_note_list_item

    override fun onBindViewHolder(holder: NoteListItemViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.onBind(list[position], position, onItemClickListener)
    }

    /**
     *
     */
    class NoteListItemViewHolder(private val binding: LayoutNoteListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(
            data: Note,
            position: Int,
            onItemClickListener: OnItemClickListener?
        ) {
            binding.viewModel = NoteItemViewModel(data, position, onItemClickListener)
            binding.tvEdit.visibility = if (data.isNoteUpdated) View.VISIBLE else View.GONE
            binding.executePendingBindings()
        }

    }
}