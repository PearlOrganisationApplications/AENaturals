package com.aenatural.aenaturals.myspalon

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import com.aenatural.aenaturals.R
import com.aenatural.aenaturals.apiservices.MSAddStaffService
import com.aenatural.aenaturals.baseframework.BaseClass
import com.aenatural.aenaturals.baseframework.Session
import com.aenatural.aenaturals.common.RetrofitClient.retrofit
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MSAddBeauticians : BaseClass() {
    lateinit var beauticians_back: ImageButton
    lateinit var input_medicineET: Spinner
    private lateinit var adapterSpinner: ArrayAdapter<String>
    private lateinit var dataList: List<String>
    lateinit var ms_addbeautician_salutation: Spinner
    lateinit var ms_addbeautician_fullname: EditText
    lateinit var ms_addbeautician_mobile: EditText
    lateinit var ms_addbeautician_gender_rg: RadioGroup
    lateinit var ms_addbeautician_qualification: EditText
    lateinit var ms_addbeautician_profession: EditText
    lateinit var ms_addbeautician_experience: EditText
    lateinit var ms_addbeautician_appointment_intervel: EditText
    lateinit var ms_addbeautician_save: TextView
    lateinit var session: Session
    lateinit var MS_addbeautican_errorTV:TextView
    lateinit var loadingDialog: DialogPB

    var salutation = "Mr"
    var fullname = ""
    var mobile = ""
    var gender = "male"
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
        input_medicineET = findViewById(R.id.ms_addbeautician_salutation)

        dataList = listOf("Mr", "Mrs", "Miss", "Ms", "Dr")
        adapterSpinner = ArrayAdapter(this, R.layout.spinner_dropdown_item, dataList)
        input_medicineET.adapter = adapterSpinner

        ms_addbeautician_salutation = findViewById(R.id.ms_addbeautician_salutation)
        ms_addbeautician_fullname = findViewById(R.id.ms_addbeautician_fullname)
        ms_addbeautician_mobile = findViewById(R.id.ms_addbeautician_mobile)
        ms_addbeautician_gender_rg = findViewById(R.id.ms_addbeautician_gender_rg)
        ms_addbeautician_qualification = findViewById(R.id.ms_addbeautician_qualification)
        ms_addbeautician_profession = findViewById(R.id.ms_addbeautician_profession)
        ms_addbeautician_experience = findViewById(R.id.ms_addbeautician_experience)
        ms_addbeautician_appointment_intervel =
            findViewById(R.id.ms_addbeautician_appointment_intervel)
        ms_addbeautician_save = findViewById(R.id.ms_addbeautician_save)

        MS_addbeautican_errorTV = findViewById(R.id.MS_addbeautican_errorTV)
        MS_addbeautican_errorTV.visibility = View.GONE
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
        loadingDialog.startLoadingDialog()
        MS_addbeautican_errorTV.visibility = View.GONE
        GlobalScope.launch {
            val tokn = session.token
            var apiService = retrofit.create(MSAddStaffService::class.java)

            try {
                var response = apiService.addStaff("Bearer $tokn",
                    salutation,
                    fullname,
                    mobile,
                    qualification,
                    profession,
                    experience,
                    intervel,
                    gender
                )

                Log.d("RegisterBeautician",response.toString())
                loadingDialog.dismissDialog()
                Log.d("ResStatus ",response.status)
                if(response.status.toString() == "false") {
                    MS_addbeautican_errorTV.visibility = View.VISIBLE
                    errorHandler(response.message.toString(), MS_addbeautican_errorTV, true)
                }

            }catch (e:Exception){

            }

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

        ms_addbeautician_gender_rg.setOnCheckedChangeListener { group, checkedId ->
            val radioButton = findViewById<RadioButton>(checkedId)
            gender = radioButton.text.toString()
        }

        input_medicineET.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                salutation = dataList[position] // Retrieve the selected value
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Handle case when nothing is selected (optional)
            }
        }
    }

    override fun initializeLabels() {
    }
}