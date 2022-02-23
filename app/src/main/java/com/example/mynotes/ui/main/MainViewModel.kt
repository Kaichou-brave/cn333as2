package com.example.mynotes.ui.main

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import com.example.mynotes.models.NoteList

class MainViewModel(private val sharedPreference: SharedPreferences) : ViewModel() {
    lateinit var onListAdded: (() -> Unit)

    val lists: MutableList<NoteList> by lazy {
        retrieveLists()
    }

    private fun retrieveLists(): MutableList<NoteList> {
        val sharedPreferenceContents = sharedPreference.all
        val noteLists = ArrayList<NoteList>()

        for (taskList in sharedPreferenceContents) {
            val itemHashSet = ArrayList(taskList.value as HashSet<String>)
            val list = NoteList(taskList.key, itemHashSet)
            noteLists.add(list)
        }
        return noteLists
    }

    fun saveList(list: NoteList) {
        sharedPreference.edit().putStringSet(list.name, list.task.toHashSet()).apply()
        lists.add(list)
        onListAdded.invoke()
    }
}