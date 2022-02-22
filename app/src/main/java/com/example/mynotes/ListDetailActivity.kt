package com.example.mynotes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.mynotes.databinding.ListDetailActivityBinding
import com.example.mynotes.databinding.ListDetailFragmentBinding
import com.example.mynotes.models.NoteList
import com.example.mynotes.ui.detail.ListDetailFragment
import com.example.mynotes.ui.detail.ListDetailViewModel

class ListDetailActivity : AppCompatActivity() {

    lateinit var list: NoteList
    private lateinit var binding: ListDetailActivityBinding
    private lateinit var viewModel: ListDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[ListDetailViewModel::class.java]
        setContentView(R.layout.list_detail_activity)
        viewModel.list = intent.getParcelableExtra(MainActivity.INTENT_LIST_KEY)!!
        title = viewModel.list.name

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ListDetailFragment.newInstance())
                .commitNow()
        }
    }
}