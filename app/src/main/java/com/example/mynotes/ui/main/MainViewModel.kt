package com.example.mynotes.ui.main

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import com.example.mynotes.models.NoteList

class MainViewModel(private val sharedPreferences: SharedPreferences) : ViewModel() {
    lateinit var onListAdded: (() -> Unit)

    val lists: MutableList<NoteList> by lazy {
        retrieveLists()
    }

    private fun retrieveLists(): MutableList<NoteList> {
        val sharedPreferencesContents = sharedPreferences.all
        val NoteLists = ArrayList<NoteList>()

        for (NoteList in sharedPreferencesContents) {

            val list = NoteList(NoteList.key, NoteList.value.toString())
            NoteLists.add(list)
        }

        return NoteLists
    }

    fun saveList(list: NoteList) {
        sharedPreferences.edit().putString(list.name, list.Notes).apply()
        lists.add(list)
        onListAdded.invoke()
    }
}