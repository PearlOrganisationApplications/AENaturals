package com.aenatural.aenaturals.salesmans

import android.Manifest
import android.Manifest.permission.CALL_PHONE
import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.provider.Settings
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Button
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
import androidx.core.content.FileProvider
import com.aenatural.aenaturals.R
import com.aenatural.aenaturals.baseframework.BaseClass
import com.aenatural.aenaturals.baseframework.Session
import com.aenatural.aenaturals.common.Login
import com.yalantis.ucrop.UCrop
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import kotlin.properties.Delegates


class SalesmanProfileActivity : BaseClass() {

    lateinit var backTV: TextView
    lateinit var salesmanLogout: TextView
    lateinit var customercarebutton: CardView
    lateinit var privacypolicyButton: CardView
    lateinit var callus: CardView
    lateinit var mailus: CardView
    lateinit var whatsapp: CardView
    lateinit var customercareLayout: ScrollView
    lateinit var privacypolicylayout: ScrollView
    lateinit var alertDialog: AlertDialog.Builder

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
    lateinit var sale_button: Button
    lateinit var sale_adharPic: ImageView
    lateinit var sale_panImage: ImageView

//    private val cameraRequest = 188
    private var IMAGE_TYPE by Delegates.notNull<Int>()
   /* private val REQUEST_IMAGE_CAPTURE = 101
    private val REQUEST_IMAGE_GALLERY = 102*/
    var pref: Session? = null

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pref = Session(this);
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

        sale_button = findViewById(R.id.sale_button)
        sale_adharPic = findViewById(R.id.sale_adharPic)
        sale_panImage = findViewById(R.id.sale_panImage)

        alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle("Alert")
        alertDialog.setMessage("Do you want to Logout?")
        alertDialog.setPositiveButton("Yes") { dialogInterface, _ ->
            run {
                pref?.clearSession();
                startActivity(Intent(this, Login::class.java))
                dialogInterface.dismiss()
            }
        }
        alertDialog.setNegativeButton("No") { dialogInterface, _ ->
            run {
                //startActivity(Intent(this, SalesmanDashboard::class.java))
                dialogInterface.dismiss()
            }
        }
    }

    @SuppressLint("QueryPermissionsNeeded", "SuspiciousIndentation")
    @RequiresApi(Build.VERSION_CODES.M)
    override fun initializeClickListners() {
        backTV.setOnClickListener {
            onBackPressed()
        }

        salesmanLogout.setOnClickListener {
            alertDialog.show()
        }
        privacypolicyButton.setOnClickListener {
            if (privacypolicylayout.visibility == View.GONE) {
                privacypolicylayout.visibility = View.VISIBLE
            } else {
                privacypolicylayout.visibility = View.GONE

                customercareLayout.visibility = View.GONE
            }
        }
        customercarebutton.setOnClickListener {
            if (customercareLayout.visibility == View.GONE) {
                customercareLayout.visibility = View.VISIBLE
            } else {
                customercareLayout.visibility = View.GONE
                privacypolicylayout.visibility = View.GONE
            }
        }

        callus.setOnClickListener {
            val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "+918046444999"))
            if (ContextCompat.checkSelfPermission(
                    getApplicationContext(),
                    CALL_PHONE
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                startActivity(intent);
            } else {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CALL_PHONE), 1)
                if (ContextCompat.checkSelfPermission(
                        getApplicationContext(),
                        CALL_PHONE
                    ) == PackageManager.PERMISSION_GRANTED
                ) {
                }
                startActivity(intent);
            }
        }
        mailus.setOnClickListener {
            val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:" + "care@aenaturals.in")
            }
            startActivity(emailIntent)
        }
        whatsapp.setOnClickListener {
            val number = "8553463261"

            val intent = Intent(Intent.ACTION_VIEW)
            val url = "https://api.whatsapp.com/send?phone=$number"
            intent.data = Uri.parse(url)


            try {
                startActivity(intent)
            } catch (_: Exception) {

                Toast.makeText(this, "WhatsApp is not installed on your device", Toast.LENGTH_SHORT)
                    .show()
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
            sale_button.visibility = View.VISIBLE

            sale_adharcardPic.isEnabled = true
            sale_panCardPic.isEnabled = true
            /*  if (ContextCompat.checkSelfPermission(applicationContext, Manifest.permission.CAMERA)
                  == PackageManager.PERMISSION_DENIED)
                  ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), cameraRequest)*/

            sale_adharcardPic.setOnClickListener {

                /*val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(cameraIntent, cameraRequest)*/
                IMAGE_TYPE = 2
                requestCameraPermission()
            }

            sale_panCardPic.setOnClickListener {

                IMAGE_TYPE = 1
                requestCameraPermission()
            }

        }
        sale_button.setOnClickListener {

            sale_emailLL.visibility = View.VISIBLE
            sale_phoneNoLL.visibility = View.VISIBLE
            sale_addressLL.visibility = View.VISIBLE
            sale_cityTVLL.visibility = View.VISIBLE
            sale_stateLL.visibility = View.VISIBLE
            sale_instaIdLl.visibility = View.VISIBLE
            sale_instaIdLl.visibility = View.VISIBLE
            sale_adharNoLL.visibility = View.VISIBLE
            sale_panLL.visibility = View.VISIBLE
            sale_edtProfile.visibility = View.VISIBLE

            sale_emailEdt.visibility = View.GONE
            sale_phoneNoEdt.visibility = View.GONE
            sale_addressEdt.visibility = View.GONE
            sale_cityEdtLL.visibility = View.GONE
            sale_stateEdt.visibility = View.GONE
            sale_instaIdEdt.visibility = View.GONE
            sale_adharNoEdt.visibility = View.GONE
            sale_panEdt.visibility = View.GONE
            sale_button.visibility = View.GONE

            sale_adharcardPic.isEnabled = false
            sale_panCardPic.isEnabled = false

        }

    }

    override fun initializeInputs() {

    }

    override fun initializeLabels() {

    }




    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                REQUEST_IMAGE_CAPTURE -> {
                    val imageBitmap = data?.extras?.get("data") as Bitmap
                    val imageUri = saveImageToFile(imageBitmap) // Save the image and get its Uri
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
                    // Process the cropped image (resultUri)
                    if (resultUri != null) {
                        if (IMAGE_TYPE == 1) {
                            sale_panImage.setImageURI(resultUri)
                        } else if (IMAGE_TYPE == 2) {
                            sale_adharPic.setImageURI(resultUri)
                        }
                    }
                }
            }
        } else if (resultCode == UCrop.RESULT_ERROR) {
            val error = UCrop.getError(data!!)
            // Handle the cropping error
            printLogs("errorImage",error.toString(),"line 404 salesmanprofileActivity page")
        }
    }
}