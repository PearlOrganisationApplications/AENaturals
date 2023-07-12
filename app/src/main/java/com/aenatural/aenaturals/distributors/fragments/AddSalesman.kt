package com.aenatural.aenaturals.distributors.fragments

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.activity.OnBackPressedCallback
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import com.aenatural.aenaturals.R
import com.aenatural.aenaturals.apiservices.AddSalemanApiService
import com.aenatural.aenaturals.apiservices.datamodels.AddSalemanModel
import com.aenatural.aenaturals.apiservices.datamodels.NormalDataModel
import com.aenatural.aenaturals.baseframework.BaseFragment
import com.aenatural.aenaturals.baseframework.Session
import com.aenatural.aenaturals.common.RetrofitClient
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.yalantis.ucrop.UCrop
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream

class AddSalesman : BaseFragment() {

    private lateinit var aadhaarfront: LinearLayout
    private lateinit var aadhaarback: LinearLayout
    private lateinit var aadhaarbackPic: ImageView
    private lateinit var aadhaarfrontPic: ImageView
    private lateinit var EmailText: EditText
    private lateinit var ContactText: EditText
    private lateinit var AddressText: EditText
    private lateinit var Pan_noText: EditText
    private lateinit var male: RadioButton
    private lateinit var female: RadioButton
    private lateinit var others: RadioButton
    private lateinit var email: String
    private lateinit var contact: String
    private lateinit var address: String
    private lateinit var pan_no: String
    private lateinit var gender: String
    private lateinit var resultUri: Uri
    private var image_base64_result: String = RetrofitClient.imageString
    private lateinit var bitmap: Bitmap
    private lateinit var aadhar_front: String
    private lateinit var aadhar_back: String
    private lateinit var Submit: TextView
    lateinit var session: Session
    private lateinit var distributorFormSubmit: CardView

    companion object {

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_salesman, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        backPress()
        session = Session(requireContext())
        requireActivity().findViewById<LinearLayout>(R.id.headerdistributor).visibility = View.GONE
        initViews(view)
        initClickListener(view)
        initDataModels(view)
        initRecyclerAdapter(view)
    }

    private fun backPress() {
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(
                            R.id.DashboardFrameLayout,
                            DistributorHomeFrag()
                        ).commit()
                }
            })
    }

    private fun initRecyclerAdapter(view: View) {

    }

    private fun initDataModels(view: View) {


    }


    @RequiresApi(Build.VERSION_CODES.M)
    private fun initClickListener(view: View) {


        aadhaarfrontPic.setOnClickListener {
            imageType = 1
            requestCameraPermission()
        }
        aadhaarback.setOnClickListener {
            imageType = 2
            requestCameraPermission()
        }

        Submit.setOnClickListener {
            email = EmailText.text.toString().trim()
            contact = ContactText.text.toString().trim()
            address = AddressText.text.toString().trim()
            pan_no = Pan_noText.text.toString().trim()
            if (male.isChecked) {
                gender = "male"
            }else if (female.isChecked) {
                gender = "female"
            }else{
                gender = "other"
            }

            callApi()
        }
        /* var m = 0
         var f = 0
         distributor_profile_pic.setOnClickListener {
             m = 1
             if(f==1)
             {
                 f=0
                 distributor_parlorpic.setBackgroundResource(R.drawable.loginedittextbg)
             }
             distributor_profile_pic.setBackgroundResource(R.drawable.tapcurvedbackground)
         }
         distributor_parlorpic.setOnClickListener {
             f=1
             if(m==1)
             {
                 m=0
                 distributor_profile_pic.setBackgroundResource(R.drawable.loginedittextbg)
             }
             distributor_parlorpic.setBackgroundResource(R.drawable.tapcurvedbackground)
         }

         distributorFormSubmit.setOnClickListener {
             startActivity(Intent(requireContext(), DistributorDashboard::class.java))
         }*/

    }

    private fun callApi() {

        val apiService = RetrofitClient.retrofit.create(AddSalemanApiService::class.java)
        val dataModal =
            AddSalemanModel(
                email, contact, address, pan_no, gender,
                       image_base64_result,
                        image_base64_result)


        apiService.addSaleman("Bearer ${session.token}", dataModal)
            .enqueue(object : Callback<NormalDataModel> {
                override fun onResponse(
                    call: Call<NormalDataModel>,
                    response: Response<NormalDataModel>
                ) {

                    Log.d("addsaleman", response.body()!!.message.toString())
                }

                override fun onFailure(call: Call<NormalDataModel>, t: Throwable) {
                    Toast.makeText(requireContext(), t.stackTraceToString(), Toast.LENGTH_SHORT)
                        .show()
                    Log.d("addsaleman1221",t.localizedMessage.toString() )
                }


            })


    }

    /*override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
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
                            1  -> {
                                Glide.with(this)
                                    .load(resultUri)
                                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                                    .skipMemoryCache(true)
                                    .into(aadhaarfrontPic)

                                Log.d("image111",resultUri.toString())

                            }
                            2 -> {
                                Glide.with(this)
                                    .load(resultUri)
                                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                                    .skipMemoryCache(true)
                                    .into(aadhaarbackPic)

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
    }*/


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
                    resultUri = UCrop.getOutput(data!!)!!
                    val imageStream = requireActivity().contentResolver.openInputStream(resultUri);
                    bitmap = BitmapFactory.decodeStream(imageStream);
                    image_base64_result = encodeImage(bitmap)!!
                    if (resultUri != null) {
                        when (imageType) {
                            1 -> {
                                Glide.with(this)
                                    .load(resultUri)
                                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                                    .skipMemoryCache(true)
                                    .into(aadhaarfrontPic)

                                Glide.with(this)
                                    .load(resultUri)
                                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                                    .skipMemoryCache(true)
                                    .into(aadhaarfrontPic)
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

    private fun encodeImage(bm: Bitmap): String? {
        val baos = ByteArrayOutputStream()
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val b: ByteArray = baos.toByteArray()
        return Base64.encodeToString(b, Base64.DEFAULT)
    }

    private fun initViews(view: View) {
        /*distributor_profile_pic = view.findViewById(R.id.distributor_profile_pic)
        distributor_parlorpic = view.findViewById(R.id.distributor_parlorpic)*/
        distributorFormSubmit = view.findViewById(R.id.distributorFormSubmit)
        aadhaarfront = view.findViewById(R.id.dist_adharFrontLL)
        aadhaarback = view.findViewById(R.id.dist_adharRearLL)
        aadhaarbackPic = view.findViewById(R.id.dist_adharRearIV)
        aadhaarfrontPic = view.findViewById(R.id.dist_adharFrontIV)
        aadhaarfrontPic = view.findViewById(R.id.dist_adharFrontIV)
        Submit = view.findViewById(R.id.distributor_submitBT)
        EmailText = view.findViewById(R.id.email)
        AddressText = view.findViewById(R.id.address)
        ContactText = view.findViewById(R.id.contact)
        Pan_noText = view.findViewById(R.id.pan_no)
        male = view.findViewById(R.id.rbMale)
        female = view.findViewById(R.id.rbFemale)
        others = view.findViewById(R.id.rbOthers)
    }


    override fun onResume() {
        super.onResume()
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }

    private val callback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            // Handle the back button press here
//            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            requireActivity().supportFragmentManager.beginTransaction().replace(
                R.id.DashboardFrameLayout,
                DistributorHomeFrag()
            ).commit()
            Toast.makeText(requireContext(), "Back button pressed", Toast.LENGTH_LONG).show()
        }
    }

}