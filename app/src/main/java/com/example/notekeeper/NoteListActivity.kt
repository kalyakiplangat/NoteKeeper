package com.example.notekeeper

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_note_list.*
import kotlinx.android.synthetic.main.content_note_list.*

class NoteListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_list)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        noteList.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1,
        DataManager.notes)

        noteList.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra(EXTRA_STRING_POSITION, position)
            startActivity(intent)
        }
    }

}