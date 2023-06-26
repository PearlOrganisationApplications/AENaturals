package com.aenatural.aenaturals.customers

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aenatural.aenaturals.R
import com.aenatural.aenaturals.baseframework.BaseClass
import com.aenatural.aenaturals.baseframework.Session
import com.aenatural.aenaturals.common.Login
import com.aenatural.aenaturals.common.Models.RetailerDataModel
import com.aenatural.aenaturals.customers.adapters.CustomerProfileAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.yalantis.ucrop.UCrop

class CustomerProfileActivity : BaseClass() {

    lateinit var cust_customercareLayout: ScrollView
    lateinit var cust_privacypolicylayout: ScrollView
    lateinit var profile_bottomnav:BottomNavigationView
    lateinit var itemList: java.util.ArrayList<RetailerDataModel>
    lateinit var customerOrderHistoryRecycler:RecyclerView
    lateinit var alertDialog:AlertDialog.Builder
    var pref: Session? = null
    lateinit var edtProfile: ImageView

    lateinit var nameLL: LinearLayout
    lateinit var emailLL: LinearLayout
    lateinit var phoneNoLL: LinearLayout
    lateinit var addressLL: LinearLayout
    lateinit var cityTVLL: LinearLayout
    lateinit var cityEdtLL: LinearLayout
    lateinit var stateLL: LinearLayout
    lateinit var instaLL: LinearLayout
    lateinit var tagsLL: LinearLayout
    lateinit var profile_pic: LinearLayout
    lateinit var parlorpic: LinearLayout

    lateinit var nameEdt: EditText
    lateinit var emailEdt: EditText
    lateinit var phoneNoEdt: EditText
    lateinit var addressEdt: EditText
    lateinit var cityEdt: EditText
    lateinit var pincodeEdt: EditText
    lateinit var stateEdt: EditText
    lateinit var instaIdEdt: EditText
    lateinit var tagsEdt: EditText
    lateinit var backTV: TextView
    lateinit var cust_profilePic: ImageView
    lateinit var cust_profieIV: ImageView
    lateinit var cust_parlorIV: ImageView

    lateinit var button: Button

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pref=Session(this);
        setLayoutXml()
        initializeViews()
        initializeClickListners()
        initializeInputs()
        initializeLabels()
    }

    override fun setLayoutXml() {
        setContentView(R.layout.activity_customer_profile)
        getMidGreentheme()
    }

    override fun initializeViews() {
        profile_bottomnav = findViewById(R.id.customer_bottomnav)
        customerOrderHistoryRecycler = findViewById(R.id.customerOrderHistoryRecycler)

        cust_customercareLayout = findViewById(R.id.cust_customercareLayout)
        cust_privacypolicylayout = findViewById(R.id.cust_privacypolicylayout)

        edtProfile = findViewById(R.id.edtProfile)

        nameLL = findViewById(R.id.nameLL)
        emailLL = findViewById(R.id.emailLL)
        phoneNoLL = findViewById(R.id.phoneNoLL)
        addressLL = findViewById(R.id.addressLL)
        cityTVLL = findViewById(R.id.cityTVLL) //textView layout
        cityEdtLL = findViewById(R.id.cityEdtLL) //EditText layout
        stateLL = findViewById(R.id.stateLL)
        instaLL = findViewById(R.id.instaLL)
        tagsLL = findViewById(R.id.tagsLL)
        profile_pic = findViewById(R.id.profile_pic)
        parlorpic = findViewById(R.id.parlorpic)

        nameEdt = findViewById(R.id.nameEdt)
        emailEdt = findViewById(R.id.emailEdt)
        phoneNoEdt = findViewById(R.id.phoneNoEdt)
        addressEdt = findViewById(R.id.addressEdt)
        cityEdt = findViewById(R.id.cityEdt)
        pincodeEdt = findViewById(R.id.pincodeEdt)
        stateEdt = findViewById(R.id.stateEdt)
        instaIdEdt = findViewById(R.id.instaIdEdt)
        tagsEdt = findViewById(R.id.tagsEdt)

        button = findViewById(R.id.button)
        cust_profilePic = findViewById(R.id.cust_profilePic)
        cust_profieIV = findViewById(R.id.cust_profieIV)
        cust_parlorIV = findViewById(R.id.cust_parlorIV)
        backTV = findViewById(R.id.backTV)



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

    @RequiresApi(Build.VERSION_CODES.M)
    override fun initializeClickListners() {
        profile_bottomnav.setOnItemSelectedListener {
            when(it.itemId){
                R.id.customercare->{
                    cust_customercareLayout.isVisible = !cust_customercareLayout.isVisible
                    cust_privacypolicylayout.visibility = View.GONE
                }
                R.id.cust_privacybn->{
                    cust_privacypolicylayout.isVisible = !cust_privacypolicylayout.isVisible
                    cust_customercareLayout.visibility = View.GONE
                }
                R.id.cust_logout_bn->{
alertDialog.show()
                }

            }
            true
        }
        edtProfile.setOnClickListener {
            nameLL.visibility = View.GONE
            emailLL.visibility = View.GONE
            phoneNoLL.visibility = View.GONE
            addressLL.visibility = View.GONE
            cityTVLL.visibility = View.GONE
            stateLL.visibility = View.GONE
            instaLL.visibility = View.GONE
            tagsLL.visibility = View.GONE
            edtProfile.visibility = View.GONE

            nameEdt.visibility = View.VISIBLE
            emailEdt.visibility = View.VISIBLE
            phoneNoEdt.visibility = View.VISIBLE
            addressEdt.visibility = View.VISIBLE
            cityEdtLL.visibility = View.VISIBLE
            stateEdt.visibility = View.VISIBLE
            instaIdEdt.visibility = View.VISIBLE
            tagsEdt.visibility = View.VISIBLE
            button.visibility = View.VISIBLE

            profile_pic.isEnabled = true
            parlorpic.isEnabled = true

            profile_pic.setOnClickListener {
                imageType =1
                requestCameraPermission()
                Toast.makeText(this,"hello ",Toast.LENGTH_SHORT).show()
            }
            parlorpic.setOnClickListener {
                imageType =2
                requestCameraPermission()
                Toast.makeText(this,"hi ",Toast.LENGTH_SHORT).show()
            }


        }

        button.setOnClickListener {

            nameLL.visibility = View.VISIBLE
            emailLL.visibility = View.VISIBLE
            phoneNoLL.visibility = View.VISIBLE
            addressLL.visibility = View.VISIBLE
            cityTVLL.visibility = View.VISIBLE
            stateLL.visibility = View.VISIBLE
            instaLL.visibility = View.VISIBLE
            tagsLL.visibility = View.VISIBLE
            edtProfile.visibility = View.VISIBLE

            nameEdt.visibility = View.GONE
            emailEdt.visibility = View.GONE
            phoneNoEdt.visibility = View.GONE
            addressEdt.visibility = View.GONE
            cityEdtLL.visibility = View.GONE
            stateEdt.visibility = View.GONE
            instaIdEdt.visibility = View.GONE
            tagsEdt.visibility = View.GONE
            button.visibility = View.GONE

            profile_pic.isEnabled = false
            parlorpic.isEnabled = false
        }

        backTV.setOnClickListener {
            onBackPressed()
        }


    }

    override fun initializeInputs() {

    }

    private fun initDataModels(){
        itemList= java.util.ArrayList()
        for(i in 0..5)
            itemList.add(RetailerDataModel("Rajesh K","rajeshisamazing@gmail.com","RR Nagar Banglore","+9182384898"))
    }
    override fun initializeLabels() {
        initDataModels()
        customerOrderHistoryRecycler.adapter = CustomerProfileAdapter(itemList)
        customerOrderHistoryRecycler.layoutManager = LinearLayoutManager(this)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {

            when (requestCode) {
                REQUEST_IMAGE_CAPTURE -> {
                    val imageBitmap = data?.extras?.get("data") as Bitmap
                    val imageUri = saveImageToFile(imageBitmap)
                    if (imageUri != null) {
                        cropImage(imageUri)
                    }
                }
                REQUEST_IMAGE_GALLERY -> {
                    val imageUri = data?.data
                    if (imageUri != null) {
                        cropImage(imageUri)
                    }
                }
                UCrop.REQUEST_CROP -> {
                    val resultUri = UCrop.getOutput(data!!)
                    if (resultUri != null) {
                        when (imageType) {
                            1 -> {
                                Glide.with(this)
                                    .load(resultUri)
                                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                                    .skipMemoryCache(true)
                                    .into(cust_profieIV)

                                Glide.with(this)
                                    .load(resultUri)
                                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                                    .skipMemoryCache(true)
                                    .into(cust_profilePic)
                            }
                            2 -> {
                                Glide.with(this)
                                    .load(resultUri)
                                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                                    .skipMemoryCache(true)
                                    .into(cust_parlorIV)
                            }

                        }
                    }
                }
            }

        } else if (resultCode == UCrop.RESULT_ERROR) {
            val error = UCrop.getError(data!!)
            // Handle the cropping error
            Log.e("Error", "Crop error: $error")
        }
    }
}