package com.example.mynotes.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mynotes.databinding.ListSelectionViewHolderBinding

class ListSelectionRecycleViewAdapter : RecyclerView.Adapter<ListSelectionViewHolder>() {

    val listTitles = arrayOf("jj","is","suspicious")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListSelectionViewHolder {
        val binding = ListSelectionViewHolderBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return  ListSelectionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListSelectionViewHolder, position: Int) {
        holder.binding.itemNumber.text = (position +1).toString()
        holder.binding.itemName.text = listTitles[position]
    }

    override fun getItemCount(): Int {
        return listTitles.size
    }

}
