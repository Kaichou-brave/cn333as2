package com.example.mynotes

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.mynotes.models.NoteList
import com.example.mynotes.ui.detail.ListDetailFragment


class ListDetailActivity : AppCompatActivity() {

    lateinit var list: NoteList
    lateinit var listDetailEditText: EditText
    lateinit var sharedPreference: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_detail_activity)

        list = intent.getParcelableExtra(MainActivity.INTENT_LIST_KEY)!!
        title = list.name

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ListDetailFragment.newInstance())
                .commitNow()
        }
    }

    public override fun onPostCreate(savedInstanceState: Bundle?) {

        sharedPreference = getSharedPreferences("", MODE_PRIVATE)
        listDetailEditText = findViewById(R.id.list_detail_edit_text)

        var loadText = sharedPreference.getString(list.name, "")

        listDetailEditText.setText(loadText)
        super.onPostCreate(savedInstanceState)
    }

    override fun onBackPressed() {

        sharedPreference = getSharedPreferences("", MODE_PRIVATE)
        listDetailEditText = findViewById(R.id.list_detail_edit_text)

        var edited = listDetailEditText.text.toString()

        listDetailEditText.setText(edited)
        sharedPreference.edit().putString(list.name, edited).apply()
        super.onBackPressed()
    }

}