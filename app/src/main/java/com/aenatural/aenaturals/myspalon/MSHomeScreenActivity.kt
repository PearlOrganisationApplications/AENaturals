package com.aenatural.aenaturals.myspalon

import android.app.AlertDialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.aenatural.aenaturals.R
import com.aenatural.aenaturals.apiservices.MSGetProfileApiService
import com.aenatural.aenaturals.apiservices.datamodels.MSProfileResponseDM
import com.aenatural.aenaturals.baseframework.BaseClass
import com.aenatural.aenaturals.baseframework.Session
import com.aenatural.aenaturals.common.DialogPB
import com.aenatural.aenaturals.common.Login
import com.aenatural.aenaturals.common.RetrofitClient
import com.google.android.material.navigation.NavigationView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.system.exitProcess


class MSHomeScreenActivity : BaseClass(), NavigationView.OnNavigationItemSelectedListener {
    lateinit var ms_myprofile: LinearLayout
    lateinit var ms_home_beauticians: LinearLayout
    lateinit var ms_home_customers: LinearLayout
    lateinit var ms_home_invoice: LinearLayout
    lateinit var ms_home_services: LinearLayout
    lateinit var ms_home_calendar: LinearLayout
    lateinit var btn_drawer_menu: ImageView
    lateinit var drawerLayout: DrawerLayout
    lateinit var nav_view: NavigationView
    lateinit var loadingDialog: DialogPB
    lateinit var session: Session

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setLayoutXml()
        initializeViews()
        initializeClickListners()

        loadingDialog.startLoadingDialog()

        getProfileResponse()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun setLayoutXml() {
        setContentView(R.layout.activity_mshome_screen)
        session = Session(this)
        birdTheme()
    }

    override fun initializeViews() {
        loadingDialog = DialogPB(this)
        ms_myprofile = findViewById(R.id.ms_myprofile)
        drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)
        btn_drawer_menu = findViewById(R.id.btn_drawer_menu)
        ms_home_beauticians = findViewById(R.id.ms_home_beauticians)
        ms_home_invoice = findViewById(R.id.ms_home_invoice)
        ms_home_services = findViewById(R.id.ms_home_services)
        ms_home_customers = findViewById(R.id.ms_home_customers)
        ms_home_calendar = findViewById(R.id.ms_home_calendar)
        nav_view = findViewById(R.id.nav_view)
        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun initializeClickListners() {
        ms_myprofile.setOnClickListener {
            startActivity(Intent(this, MSEditProfileActivit::class.java))
        }
        ms_home_beauticians.setOnClickListener {
            startActivity(Intent(this, MSBeauticians::class.java))
        }
        ms_home_customers.setOnClickListener {
            startActivity(Intent(this, MSCustomersActivity::class.java))
        }
        btn_drawer_menu.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START);
        }
        ms_home_invoice.setOnClickListener {
            startActivity(Intent(this, MSInvoiceActivity::class.java))
        }
        ms_home_calendar.setOnClickListener {
            startActivity(Intent(this, MSCalendarSectionActivity::class.java))
        }
        ms_home_services.setOnClickListener {
            startActivity(Intent(this, MSServiceActivity::class.java))
        }
    }

    override fun initializeInputs() {

    }

    override fun initializeLabels() {

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.menu_item1 -> {
                startActivity(Intent(this, MSBeauticians::class.java))
            }
            R.id.menu_item2 -> {
                startActivity(Intent(this, MSCustomersActivity::class.java))
            }

            R.id.menu_appointments -> {
                startActivity(Intent(this, MSCalendarSectionActivity::class.java))
            }
            R.id.menu_invoices -> {
                startActivity(Intent(this, MSInvoiceActivity::class.java))
            }
            R.id.menu_logout -> {
                startActivity(Intent(this, Login::class.java))
                finish()
            }
        }
        // Close the drawer after handling the click event
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun getProfileResponse() {

        val apiService = RetrofitClient.retrofit.create(MSGetProfileApiService::class.java)
        val tokn = session.token
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val call: Call<MSProfileResponseDM> = apiService.getProfile("Bearer $tokn")
                call.enqueue(object : Callback<MSProfileResponseDM> {
                    override fun onResponse(
                        call: Call<MSProfileResponseDM>,
                        response: Response<MSProfileResponseDM>
                    ) {
                        loadingDialog.dismissDialog()
                        if (response.isSuccessful) {
                            val data = response.body()
                            var profilestatus = "false"
                            val profile = data?.profile
                            val email = profile?.email
                            val status = data?.status
                            val username = profile?.username
                            val fullName = profile?.fullName
                            val image = profile?.image
                            val gender = profile?.gender
                            val mobile = profile?.mobile
                            val qualification = profile?.qualification
                            val profession = profile?.profession
                            val experience = profile?.experience
                            val appointmentInterval = profile?.appointmentInterval
                            val salutation = profile?.salutation
                            if (data != null) {
                                if (data.status.equals("true")) {
                                    Log.d("ProfileResponse ", data.profile.email.toString())
                                    Log.d("Profile ", profile.toString())
                                    try {
                                        profilestatus = data.profileStatus.toString()
                                        Log.d("PResponse ", data.profileStatus.toString())
                                    } catch (_: Exception) {
                                        profilestatus = "true"
                                        Log.d("PResponse ", "true")
                                    }
                                } else {
                                    Toast.makeText(
                                        this@MSHomeScreenActivity,
                                        "Your Session has expired please login again",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    startActivity(
                                        Intent(
                                            this@MSHomeScreenActivity,
                                            Login::class.java
                                        )
                                    )
                                }

                                if (email == null || fullName == null || image == "" || gender == null || mobile == null || qualification == null || profession == null ||
                                    experience == null || appointmentInterval == null || salutation == null
                                ) {
                                    val builder = AlertDialog.Builder(this@MSHomeScreenActivity)
                                    builder.setTitle("Profile Details")
                                        .setMessage("Some fields are missing, please fill all the details")
                                        .setPositiveButton("ok") { dialog, _ ->
                                            dialog.dismiss()
                                            startActivity(
                                                Intent(
                                                    this@MSHomeScreenActivity,
                                                    MSEditProfileActivit::class.java
                                                )
                                            )
                                        }
                                    val dialog = builder.create()
                                    dialog.show()
                                }

                            } else {

                            }
                        }
                    }

                    override fun onFailure(call: Call<MSProfileResponseDM>, t: Throwable) {
                        loadingDialog.dismissDialog()
                        Log.d(
                            "FailureResponse",
                            t.message.toString() + " \n" + t.localizedMessage + " \n" + t.cause + " \n" + t.stackTraceToString()
                        )
                        Log.d("CallResponse", call.toString())
                    }
                })

            } catch (e: Exception) {
                Log.d("ExceptionResponse", e.message.toString())
            }
        }
    }
}