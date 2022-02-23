package com.example.mynotes

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModelProvider
import androidx.preference.PreferenceManager
import com.example.mynotes.databinding.MainActivityBinding
import com.example.mynotes.models.NoteList
import com.example.mynotes.ui.detail.ListDetailFragment
import com.example.mynotes.ui.main.MainFragment
import com.example.mynotes.ui.main.MainViewModel
import com.example.mynotes.ui.main.MainViewModelFactory

class MainActivity : AppCompatActivity(), MainFragment.MainFragmentInteractionListener {
    private lateinit var binding: MainActivityBinding
    private lateinit var viewModel: MainViewModel

    lateinit var listDetailEdittext: EditText
    lateinit var sharedPreference: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(
            this,
            MainViewModelFactory(PreferenceManager.getDefaultSharedPreferences(this))
        )[MainViewModel::class.java]

        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {

            val mainFragment = MainFragment.newInstance(this)
            val mainFragmentId: Int = if (binding.listDetailFragmentXl == null) {
                R.id.container
            } else {
                R.id.main_fragment_xl
            }

            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add(mainFragmentId, mainFragment)
            }
        }
        binding.taskListAddButton?.setOnClickListener {
            showCreateListDialog()
        }
    }

    private fun showCreateListDialog() {
        val dialogTitle = getString(R.string.name_of_list)
        val positiveButtonTitle = getString(R.string.create_list)

        val builder = AlertDialog.Builder(this)
        val listTitleEditText = EditText(this)
        listTitleEditText.inputType = InputType.TYPE_CLASS_TEXT

        builder.setTitle(dialogTitle)
        builder.setView(listTitleEditText)

        builder.setPositiveButton(positiveButtonTitle) { dialog, _ ->
            dialog.dismiss()
            val taskList = NoteList(listTitleEditText.text.toString())
            viewModel.saveList(taskList)
            showListDetail(taskList)
        }
        builder.create().show()
    }

    private fun showListDetail(list: NoteList) {
        if (binding.listDetailFragmentXl == null) {
            val listDetailIntent = Intent(this, ListDetailActivity::class.java)
            listDetailIntent.putExtra(INTENT_LIST_KEY, list)
            startActivity(listDetailIntent)

        } else {
            title = list.name
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                replace(R.id.list_detail_fragment_xl, ListDetailFragment.newInstance())
            }
        }
    }

    companion object {
        const val INTENT_LIST_KEY = "list"
        var LIST_NAME = "ListMaker"
    }

    override fun listItemTapped(list: NoteList) {
        LIST_NAME = list.name
        showListDetail(list)
    }


    fun LoadEditText() {
        if (binding.listDetailFragmentXl != null) {
            sharedPreference = getSharedPreferences("", MODE_PRIVATE)
            listDetailEdittext = findViewById(R.id.list_detail_edit_text)
            var loadText = sharedPreference.getString(LIST_NAME, "")
            listDetailEdittext.setText(loadText)
        }
    }

    override fun onBackPressed() {
        if (binding.listDetailFragmentXl != null) {
            sharedPreference = getSharedPreferences("", MODE_PRIVATE)
            listDetailEdittext = findViewById(R.id.list_detail_edit_text)

            var edited = listDetailEdittext.text.toString()
            sharedPreference.edit().putString(LIST_NAME, edited).apply()


            title = resources.getString(R.string.app_name)
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                remove(supportFragmentManager.findFragmentById(R.id.list_detail_fragment_xl)!!)
            }
        } else {
            super.onBackPressed()
        }
    }


}