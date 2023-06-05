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
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.aenatural.aenaturals.R
import com.aenatural.aenaturals.baseframework.BaseClass
import com.aenatural.aenaturals.baseframework.Session
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

    lateinit var sale_edtProfile: ImageView

    lateinit var sale_emailLL: LinearLayout
    lateinit var sale_phoneNoLL: LinearLayout
    lateinit var sale_addressLL: LinearLayout
    lateinit var sale_cityTVLL: LinearLayout
    lateinit var sale_cityEdtLL: LinearLayout
    lateinit var sale_stateLL: LinearLayout
    lateinit var sale_instaIdLl: LinearLayout
    lateinit var sale_adharNoLL: LinearLayout
    lateinit var sale_panLL: LinearLayout
    lateinit var sale_adharcardPic: LinearLayout
    lateinit var sale_panCardPic: LinearLayout

    lateinit var sale_emailEdt: EditText
    lateinit var sale_phoneNoEdt: EditText
    lateinit var sale_addressEdt: EditText
    lateinit var sale_cityEdt: EditText
    lateinit var sale_pincodeEdt: EditText
    lateinit var sale_stateEdt: EditText
    lateinit var sale_instaIdEdt: EditText
    lateinit var sale_adharNoEdt: EditText
    lateinit var sale_panEdt: EditText
    var pref: Session? = null
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pref=Session(this);
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

        sale_edtProfile = findViewById(R.id.sale_edtProfile)

        sale_emailLL = findViewById(R.id.sale_emailLL)
        sale_phoneNoLL = findViewById(R.id.sale_phoneNoLL)
        sale_addressLL = findViewById(R.id.sale_addressLL)
        sale_cityTVLL = findViewById(R.id.sale_cityTVLL)
        sale_cityEdtLL = findViewById(R.id.sale_cityEdtLL)
        sale_stateLL = findViewById(R.id.sale_stateLL)
        sale_instaIdLl = findViewById(R.id.sale_instaIdLl)
        sale_adharNoLL = findViewById(R.id.sale_adharNoLL)
        sale_panLL = findViewById(R.id.sale_panLL)
        sale_adharcardPic = findViewById(R.id.sale_adharcardPic)
        sale_panCardPic = findViewById(R.id.sale_panCardPic)

        sale_emailEdt = findViewById(R.id.sale_emailEdt)
        sale_phoneNoEdt = findViewById(R.id.sale_phoneNoEdt)
        sale_addressEdt = findViewById(R.id.sale_addressEdt)
        sale_cityEdt = findViewById(R.id.sale_cityEdt)
        sale_pincodeEdt = findViewById(R.id.sale_pincodeEdt)
        sale_stateEdt = findViewById(R.id.sale_stateEdt)
        sale_instaIdEdt = findViewById(R.id.sale_instaIdEdt)
        sale_adharNoEdt = findViewById(R.id.sale_adharNoEdt)
        sale_panEdt = findViewById(R.id.sale_panEdt)

        alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle("Alert")
        alertDialog.setMessage("Do you want to Logout?")
        alertDialog.setPositiveButton("Yes"){dialogInterface,_ ->
            run{
                pref?.clearSession();
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

        sale_edtProfile.setOnClickListener {
            sale_emailLL.visibility = View.GONE
            sale_phoneNoLL.visibility = View.GONE
            sale_addressLL.visibility = View.GONE
            sale_cityTVLL.visibility = View.GONE
            sale_stateLL.visibility = View.GONE
            sale_instaIdLl.visibility = View.GONE
            sale_instaIdLl.visibility = View.GONE
            sale_adharNoLL.visibility = View.GONE
            sale_panLL.visibility = View.GONE
            sale_edtProfile.visibility = View.GONE

            sale_emailEdt.visibility = View.VISIBLE
            sale_phoneNoEdt.visibility = View.VISIBLE
            sale_addressEdt.visibility = View.VISIBLE
            sale_cityEdtLL.visibility = View.VISIBLE
            sale_stateEdt.visibility = View.VISIBLE
            sale_instaIdEdt.visibility = View.VISIBLE
            sale_adharNoEdt.visibility = View.VISIBLE
            sale_panEdt.visibility = View.VISIBLE

            sale_adharcardPic.setOnClickListener {

            }

            sale_panCardPic.setOnClickListener {

            }

        }

    }

    override fun initializeInputs() {

    }

    override fun initializeLabels() {

    }


}