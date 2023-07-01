package com.aenatural.aenaturals.myspalon

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import com.aenatural.aenaturals.R
import com.aenatural.aenaturals.apiservices.MSAddStaffService
import com.aenatural.aenaturals.apiservices.datamodels.AddBeauticianDM
import com.aenatural.aenaturals.apiservices.datamodels.NormalDataModel
import com.aenatural.aenaturals.baseframework.BaseClass
import com.aenatural.aenaturals.baseframework.Session
import com.aenatural.aenaturals.common.DialogPB
import com.aenatural.aenaturals.common.RetrofitClient.mainScope
import com.aenatural.aenaturals.common.RetrofitClient.retrofit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MSAddBeauticians : BaseClass() {
    lateinit var beauticians_back: ImageButton
    lateinit var ms_addbeautician_salutation: Spinner
    private lateinit var adapterSpinner: ArrayAdapter<String>
    private lateinit var dataList: List<String>

    lateinit var ms_addbeautician_fullname: EditText
    lateinit var ms_addbeautician_mobile: EditText
    lateinit var ms_addbeautician_gender_rg: RadioGroup
    lateinit var bea_maleRB: RadioButton
    lateinit var bea_femaleRB: RadioButton
    lateinit var ms_addbeautician_qualification: EditText
    lateinit var ms_addbeautician_profession: EditText
    lateinit var ms_addbeautician_experience: EditText
    lateinit var ms_addbeautician_appointment_intervel: EditText
    lateinit var ms_addbeautician_save: TextView
    lateinit var session: Session
    lateinit var loadingDialog: DialogPB


    var salutation = ""
    var fullname = ""
    var mobile = ""
    var gender = ""
    var qualification = ""
    var profession = ""
    var experience = ""
    var intervel = ""
    var save = ""

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setLayoutXml()
        initializeViews()
        initializeInputs()
        initializeClickListners()
        session = Session(this)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun setLayoutXml() {
        setContentView(R.layout.activity_msadd_beauticians)
        birdTheme()
    }

    override fun initializeViews() {
        loadingDialog = DialogPB(this)
        beauticians_back = findViewById(R.id.beauticians_back)
        ms_addbeautician_salutation = findViewById(R.id.ms_addbeautician_salutation)

        dataList = listOf("Mr", "Mrs", "Miss", "Ms", "Dr")
        adapterSpinner = ArrayAdapter(this, R.layout.spinner_dropdown_item, dataList)
        ms_addbeautician_salutation.adapter = adapterSpinner



        ms_addbeautician_fullname = findViewById(R.id.ms_addbeautician_fullname)
        ms_addbeautician_mobile = findViewById(R.id.ms_addbeautician_mobile)
        ms_addbeautician_gender_rg = findViewById(R.id.ms_addbeautician_gender_rg)
        ms_addbeautician_qualification = findViewById(R.id.ms_addbeautician_qualification)
        ms_addbeautician_profession = findViewById(R.id.ms_addbeautician_profession)
        ms_addbeautician_experience = findViewById(R.id.ms_addbeautician_experience)
        ms_addbeautician_appointment_intervel =
            findViewById(R.id.ms_addbeautician_appointment_intervel)
        ms_addbeautician_save = findViewById(R.id.ms_addbeautician_save)
        bea_maleRB = findViewById(R.id.bea_maleRB)
        bea_femaleRB = findViewById(R.id.bea_femaleRB)

//      MS_addbeautican_errorTV = findViewById(R.id.MS_addbeautican_errorTV)
    }

    override fun initializeClickListners() {
        beauticians_back.setOnClickListener {
            onBackPressed()
        }
        ms_addbeautician_save.setOnClickListener {


            sendDatatoApi()
        }
    }

    private fun sendDatatoApi() {

        initializeInputs()
//        val coroutineScope = CoroutineScope(Dispatchers.Main)

        loadingDialog.startLoadingDialog()
        val tokn = session.token
        val salutation1 = salutation
        val fullname1 = fullname
        val mobile1 = mobile
        val qualification = qualification
        val profession = profession
        val experience = experience
        val intervel = intervel
        val gender = gender
        val apiService = retrofit.create(MSAddStaffService::class.java)

        try {
            val call = apiService.addStaff(
                "Bearer $tokn",
                salutation1,
                fullname1,
                mobile1,
                qualification,
                profession,
                experience,
                intervel,
                gender
            )

            call.enqueue(object : Callback<AddBeauticianDM> {
                override fun onResponse(
                    call: Call<AddBeauticianDM>,
                    response: Response<AddBeauticianDM>
                ) {
                    if (response.isSuccessful) {
                        logHandler("RegisterBeautician", response.toString())
                        loadingDialog.dismissDialog()

                        val status = response.body()?.status
                        val msg = response.body()?.message
                        println(status)
                        if (status.equals("false")) {
                            loadingDialog.showErrorBottomSheetDialog(response.body()?.message.toString())
                        } else loadingDialog.startSucessDialog(
                            msg.toString(),
                            this@MSAddBeauticians,
                            MSBeauticians::class.java
                        )
                    } else {
                        loadingDialog.dismissDialog()
                        val errorBody = response.errorBody()?.string()
                        if (errorBody != null) {
                            Log.d("Error", errorBody)
                            loadingDialog.showErrorBottomSheetDialog(errorBody)
                        }
                        loadingDialog.showErrorBottomSheetDialog(errorBody + "Error")
                    }
                }

                override fun onFailure(call: Call<AddBeauticianDM>, t: Throwable) {
                    Log.e("API Error", t.message ?: "Unknown error")
                    loadingDialog.dismissDialog()
                    loadingDialog.showErrorBottomSheetDialog(t.message.toString())
                }

            })
        } catch (e: Exception) {
            logHandler("ExceptionResponse", e.message.toString())
            loadingDialog.dismissDialog()
            loadingDialog.showErrorBottomSheetDialog(e.message.toString())
        }


    }


    override fun initializeInputs() {
//        salutation = ms_addbeautician_salutation.text.toString()
        fullname = ms_addbeautician_fullname.text.toString()
        mobile = ms_addbeautician_mobile.text.toString()
        qualification = ms_addbeautician_qualification.text.toString()
        profession = ms_addbeautician_profession.text.toString()
        experience = ms_addbeautician_experience.text.toString()
        intervel = ms_addbeautician_appointment_intervel.text.toString()

/*        ms_addbeautician_gender_rg.setOnCheckedChangeListener { group, checkedId ->
            val radioButton = group.findViewById<RadioButton>(checkedId)
            gender = radioButton.text.toString()
        }*/
        if (bea_maleRB.isChecked) {
            gender = "male"
        } else if (bea_femaleRB.isChecked) {
            gender = "female"
        }

        ms_addbeautician_salutation.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    salutation = dataList[position]
                    salutation =
                        adapterSpinner.getItem(position).toString()// Retrieve the selected value
                    Log.d("Salutation ", salutation + " " + position)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // Handle case when nothing is selected (optional)
                }
            }

    }

    override fun initializeLabels() {
    }

    override fun onDestroy() {
        super.onDestroy()
        mainScope.cancel()
    }
}

/*
mainScope.launch {
            val tokn = session.token
            var apiService = retrofit.create(MSAddStaffService::class.java)

            try {
                if(gender=="")
                    gender = "male"
                val response = apiService.addStaff("Bearer $tokn",
                    salutation,
                    fullname,
                    mobile,
                    qualification,
                    profession,
                    experience,
                    intervel,
                    gender
                )

                logHandler("RegisterBeautician",response.toString())
                loadingDialog.dismissDialog()

                var status =response.status
                println(status)
                if(status.equals("false")) {
                   // Toast.makeText(this@MSAddBeauticians,"Status "+response.status,Toast.LENGTH_SHORT).show()
                    loadingDialog.showErrorBottomSheetDialog(response.message.toString())

                    //errorHandler(response.message.toString(), MS_addbeautican_errorTV, true)
                }

            }catch (e:Exception){
                println("catch $e")
            }

        }
 */