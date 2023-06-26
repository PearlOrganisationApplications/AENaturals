package com.aenatural.aenaturals.myspalon

import android.os.Bundle
import android.widget.SearchView
import com.aenatural.aenaturals.R
import com.aenatural.aenaturals.baseframework.BaseClass

class CreateInvoide : BaseClass() {
    lateinit var beauticians_search: SearchView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setLayoutXml()
        initializeViews()
        initializeClickListners()
    }

    override fun setLayoutXml() {
        setContentView(R.layout.activity_create_invoide)
        birdTheme()
    }

    override fun initializeViews() {
        beauticians_search = findViewById(R.id.beauticians_search)
    }

    override fun initializeClickListners() {
        // Enable user input in the SearchView
        beauticians_search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                // Handle the search query submission
                // You can perform search operations here
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                // Handle the search query text change
                // You can perform filtering or suggestions based on the input
                return true
            }
        })
    }

    override fun initializeInputs() {

        TODO("Not yet implemented")
    }

    override fun initializeLabels() {

        TODO("Not yet implemented")
    }
}