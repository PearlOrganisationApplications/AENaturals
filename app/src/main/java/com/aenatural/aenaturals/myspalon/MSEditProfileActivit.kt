package com.aenatural.aenaturals.myspalon

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.aenatural.aenaturals.R
import com.aenatural.aenaturals.apiservices.MSGetProfileApiService
import com.aenatural.aenaturals.apiservices.MSUpdateProfileApiService
import com.aenatural.aenaturals.apiservices.datamodels.MSProfileResponseDM
import com.aenatural.aenaturals.apiservices.datamodels.ProfileUpdateResponse
import com.aenatural.aenaturals.baseframework.BaseClass
import com.aenatural.aenaturals.baseframework.Session
import com.aenatural.aenaturals.common.DialogPB
import com.aenatural.aenaturals.common.RetrofitClient.retrofit
import retrofit2.*

class MSEditProfileActivit : BaseClass() {
    lateinit var profile_backIB: ImageButton
    lateinit var ms_profile_pb:LinearLayout
    lateinit var session: Session
    private lateinit var input_salutationET: Spinner
    private lateinit var input_fullname: EditText
    private lateinit var input_email: EditText
    private lateinit var input_mobile: EditText
    private lateinit var input_quali: EditText
    private lateinit var input_profession: EditText
    private lateinit var input_experience: EditText
    private lateinit var input_appointment: EditText
    private lateinit var profile_maleRB: RadioButton
    private lateinit var profile_femaleRB: RadioButton

    private lateinit var tv_submit: TextView
    lateinit var loadingDialog: DialogPB
    var fullName = ""
    var gender = ""
    var mobile = ""
    var qualification = ""
    var profession = ""
    var experience = ""
    var appointmentInterval = ""
    var salutation = ""

    private lateinit var adapterSpinner: ArrayAdapter<String>
    private lateinit var dataList: List<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setLayoutXml()
        initializeViews()
        initializeClickListners()
        getProfileResponse()
    }


    private fun getProfileResponse() {

        val apiService = retrofit.create(MSGetProfileApiService::class.java)
        val tkn = session.token

        try {
            val call:Call<MSProfileResponseDM> = apiService.getProfile("Bearer $tkn")
            call.enqueue(object:Callback<MSProfileResponseDM>{
                override fun onResponse(
                    call: Call<MSProfileResponseDM>,
                    response: Response<MSProfileResponseDM>
                ) {
                    ms_profile_pb.visibility = View.GONE
                    if(response.isSuccessful){
                        val data = response.body()
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
                            Log.d("ProfileResponse ",data.profile.email.toString())
//                            input_medicineET.setText(salutation)
                            val salutationIntex = dataList.indexOf(salutation)
                            input_salutationET.setSelection(salutationIntex)
                            input_email.setText(email)
                            input_fullname.setText(fullName)
                            input_mobile.setText(mobile)
                            input_quali.setText(qualification)
                            input_profession.setText(profession)
                            input_experience.setText(experience)
                            input_appointment.setText(appointmentInterval)
                            if (gender == "Male" || gender == "male") {
                                profile_maleRB.isChecked = true
                            } else if (gender == "Female" || gender == "female") {
                                profile_femaleRB.isChecked = true
                            }else{
                                profile_maleRB.isChecked = false
                                profile_femaleRB.isChecked = false
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<MSProfileResponseDM>, t: Throwable) {
                    ms_profile_pb.visibility = View.GONE
                    Log.d("FailureResponse",t.message.toString()+" \n"+t.localizedMessage+" \n"+t.cause+" \n"+t.stackTraceToString())
                    Log.d("CallResponse",call.toString())
                }
            })

        }catch (e:Exception){
            Log.d("ExceptionResponse",e.message.toString())
        }
    }


    private fun sendProfileData() {
        val apiService = retrofit.create(MSUpdateProfileApiService::class.java)
        val tkn = session.token

       /* val requestBody = ProfileUpdateRequest(
            fullName = fullName,  // Set your desired values here
            gender = gender,
            mobile = mobile,
            qualification = qualification,
            profession = profession,
            experience = experience,
            appointment_interval = appointmentInterval,
            salutation = salutation
        )*/

        try {
            val call = apiService.updateProfile(
                "Bearer $tkn",
                fullName = fullName,  // Set your desired values here
                gender = gender,
                mobile = mobile,
                qualification = qualification,
                profession = profession,
                experience = experience,
                appointmentInterval = appointmentInterval,
                salutation = salutation)
//            Log.d("RequestBody",requestBody.toString())
            call.enqueue(object : Callback<ProfileUpdateResponse> {
                override fun onResponse(call: Call<ProfileUpdateResponse>, response: Response<ProfileUpdateResponse>) {
                    loadingDialog.dismissDialog()
                    if (response.isSuccessful) {
                        val profileUpdateResponse = response.body()
                        val status = profileUpdateResponse?.status
                        val message = profileUpdateResponse?.message
                        // Handle the response data as needed
                        Log.d("profileUpdate",profileUpdateResponse.toString())
                        Toast.makeText(this@MSEditProfileActivit, "successful", Toast.LENGTH_SHORT).show()
                    } else {
                        // Handle the error case
                    }
                }
                override fun onFailure(call: Call<ProfileUpdateResponse>, t: Throwable) {
                    loadingDialog.dismissDialog()
                    Log.d("FailureResponse",t.message.toString()+" \n"+t.localizedMessage+" \n"+t.cause+" \n"+t.stackTraceToString())
                    Log.d("CallResponse",call.toString())
                }
            })
            }catch (e:Exception){
            Log.d("ExceptionResponse",e.message.toString())
        }

    }

    override fun setLayoutXml() {
        setContentView(R.layout.activity_msedit_profile)
        birdTheme()
        session = Session(this)
        loadingDialog = DialogPB(this)
    }

    override fun initializeViews() {
        profile_backIB = findViewById(R.id.profile_profile_backIB)
        ms_profile_pb = findViewById(R.id.profile_ms_profile_pb)
        // edit text
        input_salutationET = findViewById(R.id.profile_input_medicineET)
        input_fullname = findViewById(R.id.profile_input_fullname)
        input_email = findViewById(R.id.profile_input_email)
        input_mobile = findViewById(R.id.profile_input_mobile)
        input_quali = findViewById(R.id.profile_input_quali)
        input_profession = findViewById(R.id.profile_input_profession)
        input_experience = findViewById(R.id.profile_input_experience)
        input_appointment = findViewById(R.id.profile_input_appointment)
        profile_maleRB = findViewById(R.id.profile_maleRB)
        profile_femaleRB = findViewById(R.id.profile_femaleRB)

        tv_submit = findViewById(R.id.profile_tv_submit)

        dataList = listOf("salutation","Mr", "Mrs", "Miss", "Ms", "Dr")
        adapterSpinner = ArrayAdapter(this,R.layout.spinner_dropdown_item, dataList)
        input_salutationET.adapter = adapterSpinner
    }

    override fun initializeClickListners() {
        profile_backIB.setOnClickListener {
            onBackPressed()
        }

        input_salutationET.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                salutation = parent?.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}

        }

        tv_submit.setOnClickListener {
            fullName = input_fullname.text.toString()
            if (profile_maleRB.isChecked) {
                gender = "male"
            }else if (profile_femaleRB.isChecked) {
                gender = "female"
            }
            mobile = input_mobile.text.toString()
            qualification = input_quali.text.toString()
            profession = input_profession.text.toString()
            experience = input_experience.text.toString()
            appointmentInterval = input_appointment.text.toString()
//            salutation = input_medicineET.text.toString()
            if (salutation.isNotEmpty() && fullName.isNotEmpty() && mobile.isNotEmpty() && qualification.isNotEmpty() &&
                profession.isNotEmpty() && experience.isNotEmpty() && appointmentInterval.isNotEmpty()){
                loadingDialog.startLoadingDialog()
                sendProfileData()
            }else{
                Toast.makeText(this,"please fill all the details ",Toast.LENGTH_SHORT).show()
            }
        }
    }


    override fun initializeInputs() {
    }

    override fun initializeLabels() {
    }
}