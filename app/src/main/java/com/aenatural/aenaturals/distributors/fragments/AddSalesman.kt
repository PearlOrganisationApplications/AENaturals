package com.aenatural.aenaturals.distributors.fragments

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.aenatural.aenaturals.R
import com.aenatural.aenaturals.apiservices.AddSalemanApiService
import com.aenatural.aenaturals.apiservices.CheckoutApiService
import com.aenatural.aenaturals.apiservices.datamodels.AddSalemanModel
import com.aenatural.aenaturals.apiservices.datamodels.NormalDataModel
import com.aenatural.aenaturals.baseframework.BaseFragment
import com.aenatural.aenaturals.baseframework.Session
import com.aenatural.aenaturals.common.RetrofitClient
import com.aenatural.aenaturals.distributors.DistributorDashboard
import com.aenatural.aenaturals.salesmans.fragments.ProductsFragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.yalantis.ucrop.UCrop
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddSalesman : BaseFragment() {

    private lateinit var aadhaarfront: LinearLayout
    private lateinit var aadhaarback: LinearLayout
    private lateinit var aadhaarbackPic: ImageView
    private lateinit var aadhaarfrontPic: ImageView
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
        requireActivity().findViewById<LinearLayout>(R.id.headerdistributor).visibility =View.GONE
        initViews(view)
        initClickListener(view)
        initDataModels(view)
        initRecyclerAdapter(view)
    }

    private fun backPress() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.DashboardFrameLayout,
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
            imageType=1
            requestCameraPermission()
        }
        aadhaarback.setOnClickListener{
            imageType=2
            requestCameraPermission()
        }
        Submit.setOnClickListener {
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
        val dataModal: AddSalemanModel = AddSalemanModel(name, job)
        val call: Call<NormalDataModel> = apiService.addSaleman("Bearer "+session.token.toString(),dataModal)

        call.enqueue(object : Callback<NormalDataModel?> {
            override fun onResponse(
                call: Call<NormalDataModel?>,
                response: Response<NormalDataModel?>
            ) {

            }

            override fun onFailure(call: Call<NormalDataModel?>, t: Throwable) {

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
                    val resultUri = UCrop.getOutput(data!!)
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
    }



        override fun onResume() {
        super.onResume()
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }
    private val callback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            // Handle the back button press here
//            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            requireActivity().supportFragmentManager.beginTransaction().replace(R.id.DashboardFrameLayout,
                DistributorHomeFrag()
            ).commit()
            Toast.makeText(requireContext(), "Back button pressed", Toast.LENGTH_LONG).show()
        }
    }

}