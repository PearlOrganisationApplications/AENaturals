package com.aenatural.aenaturals.salesmans

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import android.widget.Toast
import com.aenatural.aenaturals.baseframework.BaseClass
import com.pearl.aenaturals.R


class SalesmanProfileActivity : BaseClass() {

    lateinit var backTV:TextView
    lateinit var salesmanLogout:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setLayoutXml()
        initializeViews()
        initializeClickListners()
    }

    override fun setLayoutXml() {
        setContentView(R.layout.activity_salesman_profile)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = resources.getColor(R.color.midgreen)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    }

    override fun initializeViews() {
        backTV = findViewById(R.id.backTV)
        salesmanLogout = findViewById(R.id.salesmanLogout)
    }

    override fun initializeClickListners() {
        backTV.setOnClickListener {
            onBackPressed()
        }

        salesmanLogout.setOnClickListener {
            var alertDialog = AlertDialog.Builder(this)
            alertDialog.setTitle("Alert")
            alertDialog.setMessage("Do you want to Logout?")
            alertDialog.setPositiveButton("Yes"){dialogInterface,_ ->
                run{
                    startActivity(Intent(this, SalesmanDashboard::class.java))
                    dialogInterface.dismiss()
                }
            }
            alertDialog.setNegativeButton("No"){dialogInterface,_ ->
                run{
                    //startActivity(Intent(this, SalesmanDashboard::class.java))
                    dialogInterface.dismiss()
                }
            }
            alertDialog.show()
    }}

    override fun initializeInputs() {

    }

    override fun initializeLabels() {

    }


}