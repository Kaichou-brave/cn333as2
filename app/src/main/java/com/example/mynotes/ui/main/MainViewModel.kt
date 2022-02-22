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
        val NoteList = ArrayList<NoteList>()

        for (NoteList in sharedPreferencesContents) {
            val itemsHashSet = ArrayList(NoteList.value as HashSet<String>)
            val list = NoteList(NoteList.key, itemsHashSet)
        }

        return NoteList
    }

    fun saveList(list: NoteList) {
        sharedPreferences.edit().putStringSet(list.name, list.Notes.toHashSet()).apply()
        lists.add(list)
        onListAdded.invoke()
    }
}