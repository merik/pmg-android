package com.dmc.sw.presentation

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

import com.dmc.sw.R


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener(View.OnClickListener {
            onBackPressed()
        })
        supportActionBar?.setDisplayShowTitleEnabled(false)

    }

    fun setTitle(title: String) {
        findViewById<TextView>(R.id.toolbar_title).text = title
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)

        Log.i("", "Back pressed")
    }

}