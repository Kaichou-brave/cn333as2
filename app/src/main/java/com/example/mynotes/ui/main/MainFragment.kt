package com.example.mynotes.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mynotes.databinding.MainFragmentBinding
import com.example.mynotes.models.NoteList

class MainFragment(val clickListener: MainFragmentInteractionListener) : Fragment(), ListSelectionRecycleViewAdapter.ListSelectionRecycleViewClickListener {

    interface MainFragmentInteractionListener {
        fun listItemTapped(list: NoteList)
    }

    private lateinit var binding: MainFragmentBinding

    companion object {
        fun newInstance(clickListener: MainFragmentInteractionListener) = MainFragment(clickListener)
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(inflater, container, false)

        binding.listRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(
            requireActivity(),
            MainViewModelFactory(PreferenceManager.getDefaultSharedPreferences(requireActivity()))
        )[MainViewModel::class.java]

        val recycleViewAdapter = ListSelectionRecycleViewAdapter(viewModel.lists, this)
        binding.listRecyclerView.adapter = recycleViewAdapter
        viewModel.onListAdded = {
            recycleViewAdapter.listsUpdated()
        }
    }

    override fun listItemClicked(list: NoteList) {
        clickListener.listItemTapped(list)
    }

}