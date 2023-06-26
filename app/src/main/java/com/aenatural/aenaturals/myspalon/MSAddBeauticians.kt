package com.aenatural.aenaturals.myspalon

import android.os.Build
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.Spinner
import androidx.annotation.RequiresApi
import com.aenatural.aenaturals.R
import com.aenatural.aenaturals.baseframework.BaseClass

class MSAddBeauticians : BaseClass() {
    lateinit var beauticians_back: ImageButton
    lateinit var input_medicineET: Spinner
    private lateinit var adapterSpinner: ArrayAdapter<String>
    private lateinit var dataList: List<String>
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setLayoutXml()
        initializeViews()
        initializeClickListners()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun setLayoutXml() {
        setContentView(R.layout.activity_msadd_beauticians)
        birdTheme()
    }

    override fun initializeViews() {
        beauticians_back = findViewById(R.id.beauticians_back)
        input_medicineET = findViewById(R.id.input_medicineET)

        dataList = listOf("Mr", "Mrs", "Miss", "Ms", "Dr")
        adapterSpinner = ArrayAdapter(this,R.layout.spinner_dropdown_item, dataList)
        input_medicineET.adapter = adapterSpinner
    }

    override fun initializeClickListners() {
        beauticians_back.setOnClickListener {
            onBackPressed()
        }
    }

    override fun initializeInputs() {
        TODO("Not yet implemented")
    }

    override fun initializeLabels() {
        TODO("Not yet implemented")
    }
}