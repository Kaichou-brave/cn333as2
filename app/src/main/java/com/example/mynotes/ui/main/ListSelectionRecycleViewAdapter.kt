package com.example.mynotes.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mynotes.databinding.ListSelectionViewHolderBinding
import com.example.mynotes.models.NoteList

class ListSelectionRecycleViewAdapter(
    private val lists: MutableList<NoteList>,
    val clickListener: ListSelectionRecycleViewClickListener
) :
    RecyclerView.Adapter<ListSelectionViewHolder>() {


    interface ListSelectionRecycleViewClickListener {
        fun listItemClicked(list: NoteList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListSelectionViewHolder {
        val binding = ListSelectionViewHolderBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ListSelectionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListSelectionViewHolder, position: Int) {
        //holder.binding.itemNumber.text = (position + 1).toString()
        holder.binding.itemName.text = lists[position].name
        holder.itemView.setOnClickListener {
            clickListener.listItemClicked(lists[position])
        }
    }

    override fun getItemCount(): Int {
        return lists.size
    }

    fun listsUpdated() {
        notifyItemInserted(lists.size - 1)
    }
}
