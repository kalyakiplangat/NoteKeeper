package com.example.notekeeper

import android.app.TaskStackBuilder
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.jwhh.notekeeper.CourseRecyclerAdapter
import kotlinx.android.synthetic.main.activity_items.*
import kotlinx.android.synthetic.main.content_items.*

class ItemsActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var appBarConfiguration: AppBarConfiguration

    private val linearLayoutManager by lazy {
        LinearLayoutManager(this)
    }

    private val noteRecyclerAdapter by lazy {
        NoteRecyclerAdapter(this, DataManager.notes)
    }

    private val courseLayoutManager by lazy {
        GridLayoutManager(this, 2)
    }

    private val courseRecyclerAdapter by lazy {
        CourseRecyclerAdapter(this, DataManager.courses.values.toList())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_items)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener {
            startActivity(Intent(this, NoteActivity::class.java))
        }

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.navigation_drawer_open,R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        displayNote()
    }

    private fun displayNote() {
        listItem.layoutManager = linearLayoutManager
        listItem.adapter = noteRecyclerAdapter
    }

    private fun displayNCourse() {
        listItem.layoutManager = courseLayoutManager
        listItem.adapter = courseRecyclerAdapter
    }

    override fun onResume() {
        super.onResume()
        listItem.adapter?.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.items, menu)
        return true
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        item.isChecked = true
        drawer_layout.closeDrawers()
        when (item.itemId) {
            R.id.nav_notes -> {
                displayNote()
            }
            R.id.nav_courses -> {
                displayNCourse()
            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }

        }
        return true
    }

}
