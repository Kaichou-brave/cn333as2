package com.example.mynotes.ui.detail

import android.content.SharedPreferences
import android.widget.EditText
import androidx.lifecycle.ViewModel
import com.example.mynotes.models.NoteList

class ListDetailViewModel(sharedPreferences: SharedPreferences) : ViewModel() {
    lateinit var list: NoteList

}