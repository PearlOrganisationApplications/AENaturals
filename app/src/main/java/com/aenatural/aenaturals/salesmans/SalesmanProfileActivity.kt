package com.aenatural.aenaturals.salesmans

import android.Manifest
import android.Manifest.permission.CALL_PHONE
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.aenatural.aenaturals.baseframework.BaseClass
import com.aenatural.aenaturals.R
import com.aenatural.aenaturals.common.Login


class SalesmanProfileActivity : BaseClass() {

    lateinit var backTV:TextView
    lateinit var salesmanLogout:TextView
    lateinit var customercarebutton:CardView
    lateinit var privacypolicyButton:CardView
    lateinit var callus:CardView
    lateinit var mailus:CardView
    lateinit var whatsapp:CardView
    lateinit var customercareLayout:ScrollView
    lateinit var privacypolicylayout:ScrollView
    lateinit var alertDialog:AlertDialog.Builder

    @RequiresApi(Build.VERSION_CODES.M)
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
        privacypolicyButton = findViewById(R.id.privacypolicyButton)
        customercarebutton = findViewById(R.id.customercarebutton)
        privacypolicylayout = findViewById(R.id.privacypolicylayout)
        customercareLayout = findViewById(R.id.customercareLayout)

        callus = findViewById(R.id.callus)
        mailus = findViewById(R.id.mailus)
        whatsapp = findViewById(R.id.whatsapp)

        alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle("Alert")
        alertDialog.setMessage("Do you want to Logout?")
        alertDialog.setPositiveButton("Yes"){dialogInterface,_ ->
            run{
                startActivity(Intent(this, Login::class.java))
                dialogInterface.dismiss()
            }
        }
        alertDialog.setNegativeButton("No"){dialogInterface,_ ->
            run{
                //startActivity(Intent(this, SalesmanDashboard::class.java))
                dialogInterface.dismiss()
            }
        }
    }

    @SuppressLint("QueryPermissionsNeeded")
    @RequiresApi(Build.VERSION_CODES.M)
    override fun initializeClickListners() {
        backTV.setOnClickListener {
            onBackPressed()
        }

        salesmanLogout.setOnClickListener {
            alertDialog.show()
    }
        privacypolicyButton.setOnClickListener {
            if (privacypolicylayout.visibility==View.GONE)
                privacypolicylayout.visibility=View.VISIBLE
            else
                privacypolicylayout.visibility=View.GONE

            customercareLayout.visibility=View.GONE
        }
        customercarebutton.setOnClickListener {
            if (customercareLayout.visibility==View.GONE)
                customercareLayout.visibility=View.VISIBLE
            else
                customercareLayout.visibility=View.GONE
            privacypolicylayout.visibility=View.GONE

        }

        callus.setOnClickListener {
            val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "+918046444999"))
            if (ContextCompat.checkSelfPermission(getApplicationContext(), CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                startActivity(intent);
            } else {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CALL_PHONE), 1)
                if (ContextCompat.checkSelfPermission(getApplicationContext(), CALL_PHONE) == PackageManager.PERMISSION_GRANTED)
                    startActivity(intent);
            }
        }
        mailus.setOnClickListener {
            val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:"+"care@aenaturals.in")
            }
            startActivity(emailIntent)
        }
        whatsapp.setOnClickListener {
            val number = "8553463261"

            val intent = Intent(Intent.ACTION_VIEW)
            val url = "https://api.whatsapp.com/send?phone=$number"
            intent.data = Uri.parse(url)


            try{
                startActivity(intent)
            } catch (_:Exception) {

                Toast.makeText(this, "WhatsApp is not installed on your device", Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun initializeInputs() {

    }

    override fun initializeLabels() {

    }


}