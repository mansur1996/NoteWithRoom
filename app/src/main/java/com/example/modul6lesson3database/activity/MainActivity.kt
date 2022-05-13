package com.example.modul6lesson3database.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.modul6lesson3database.R
import com.example.modul6lesson3database.adapter.NoteAdapter
import com.example.modul6lesson3database.databinding.ActivityMainBinding
import com.example.modul6lesson3database.model.Note
import com.example.modul6lesson3database.database.UserRepository
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var list: ArrayList<Note>
    private lateinit var adpter: NoteAdapter
    private lateinit var repository: UserRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        repository = UserRepository(application)
        initViews()
    }

    private fun initViews() {
        showProgressBar()
        readNotesList()

        binding.fab.setOnClickListener {
            showDialog()
        }

    }

    private fun showDialog() {
        val myView = LayoutInflater.from(this).inflate(R.layout.view_dialog, null)


        MaterialAlertDialogBuilder(this)
            .setTitle("New Note")
            .setMessage("DO you want to add a new note")
            .setView(myView)
            .setPositiveButton("YES") { dialog, which ->
                val newNote = myView.findViewById<EditText>(R.id.et_new_note).text.toString().trim()
                if (newNote.isNotEmpty()) {
                    showProgressBar()
                    addNewNote(newNote)
                } else {
                    Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                }
            }.setNegativeButton("CANCEL") { dialog, which ->

            }.show()

    }

    private fun addNewNote(newNote: String) {
        val note = Note()
        note.id = System.currentTimeMillis()
        note.time = getCurrentTime()
        note.note = newNote
        repository.saveUser(note)
        list.add(note)
        adpter.notifyItemInserted(list.size)
        hideProgressBar()
        Toast.makeText(this, "Successfully added", Toast.LENGTH_SHORT).show()
    }

    private fun getCurrentTime(): String? {
        val c = Calendar.getInstance()

        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val hour = c.get(Calendar.HOUR_OF_DAY)
        val minute = c.get(Calendar.MINUTE)

        return "$day/$month/$year"
    }

    private fun readNotesList() {
        //read post from room
        list = repository.getUsers() as ArrayList<Note>
        refreshAdapter(list)
    }

    private fun refreshAdapter(list: ArrayList<Note>) {
        adpter = NoteAdapter(list)
        binding.rvNotes.adapter = adpter
        hideProgressBar()
    }

    private fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        binding.progressBar.visibility = View.GONE
    }

}



