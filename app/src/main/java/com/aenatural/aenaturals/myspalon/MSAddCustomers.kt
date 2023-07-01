package com.aenatural.aenaturals.myspalon

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.TextView
import com.aenatural.aenaturals.R
import com.aenatural.aenaturals.apiservices.MSCreateCustomerApiService
import com.aenatural.aenaturals.apiservices.datamodels.NormalDataModel
import com.aenatural.aenaturals.baseframework.BaseClass
import com.aenatural.aenaturals.baseframework.Session
import com.aenatural.aenaturals.common.DialogPB
import com.aenatural.aenaturals.common.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

open class MSAddCustomers : BaseClass() {
    private lateinit var cust_input_medicineET: EditText
    private lateinit var cust_input_mobile: EditText
    private lateinit var cust_input_email: EditText
    private lateinit var cust_input_dob: EditText
    private lateinit var cust_input_app_date: EditText
    private lateinit var cust_input_profession: EditText
    private lateinit var cust_input_experience: EditText
    private lateinit var cust_input_appointment: EditText
    private lateinit var cust_maleRB: RadioButton
    private lateinit var cust_femaleRB: RadioButton
    private lateinit var cust_appointmentCB: CheckBox
    private lateinit var cust_appointLL: LinearLayout
    private lateinit var cust_tv_submit: TextView
    private lateinit var session: Session
    private lateinit var loadingDialog: DialogPB
    var fullName = ""
    var mobile = ""
    var gender = ""
    var email = ""
    var dob = ""
    var appointmentDate = ""
    var appointmentTime = ""
    var appointmentDuration = ""
    var appointmentReason = ""


    private val myCalendar: Calendar = Calendar.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setLayoutXml()
        initializeViews()
        initializeClickListners()
    }

    override fun setLayoutXml() {
        setContentView(R.layout.activity_msadd_customers)
        birdTheme()
        session = Session(this)
        loadingDialog = DialogPB(this)
    }

    override fun initializeViews() {
        cust_input_medicineET = findViewById(R.id.cust_input_medicineET)
        cust_input_mobile = findViewById(R.id.cust_input_mobile)
        cust_input_email = findViewById(R.id.cust_input_email)
        cust_maleRB = findViewById(R.id.cust_maleRB)
        cust_femaleRB = findViewById(R.id.cust_femaleRB)
        cust_input_dob = findViewById(R.id.cust_input_dob)
        cust_appointmentCB = findViewById(R.id.cust_appointmentCB)
        cust_input_app_date = findViewById(R.id.cust_input_app_date)
        cust_input_profession = findViewById(R.id.cust_input_profession)
        cust_input_experience = findViewById(R.id.cust_input_experience)
        cust_input_appointment = findViewById(R.id.cust_input_appointment)
        cust_appointLL = findViewById(R.id.cust_appointLL)
        cust_tv_submit = findViewById(R.id.cust_tv_submit)
    }

    override fun initializeClickListners() {
        cust_appointLL.visibility = View.GONE

        cust_input_dob.setOnClickListener {
            showDatePicker()
        }
        cust_appointmentCB.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                // Show the desired views
                cust_appointLL.visibility = View.VISIBLE
                // ...
            } else {
                // Hide the desired views
                cust_appointLL.visibility = View.GONE
                // ...
            }
        }

        cust_tv_submit.setOnClickListener {
            fullName = cust_input_medicineET.text.toString()
            mobile = cust_input_mobile.text.toString()
            if (cust_maleRB.isChecked) {
                gender = "male"
            }else if (cust_femaleRB.isChecked) {
                gender = "female"
            }
            email = cust_input_email.text.toString()
            dob = cust_input_dob.text.toString()
            appointmentDate = cust_input_app_date.text.toString()
            appointmentTime = cust_input_profession.text.toString()
            appointmentDuration = cust_input_experience.text.toString()
            appointmentReason = cust_input_appointment.text.toString()

            if (fullName.isNotEmpty() && mobile.isNotEmpty() && gender.isNotEmpty() && email.isNotEmpty() && dob.isNotEmpty()) {
                loadingDialog.startLoadingDialog()
                updateCustomer()
            }else{
                loadingDialog.showErrorBottomSheetDialog("please fill all the details")
            }
        }

    }

    override fun initializeInputs() {
    }

    override fun initializeLabels() {
    }

    private fun showDatePicker() {
        val date = DatePickerDialog.OnDateSetListener { view, year, month, day ->
            myCalendar.set(Calendar.YEAR, year)
            myCalendar.set(Calendar.MONTH, month)
            myCalendar.set(Calendar.DAY_OF_MONTH, day)
            updateLabel()
        }

        val datePickerDialog = DatePickerDialog(
            this@MSAddCustomers,
            R.style.MyDatePickerDialogTheme, // use your custom theme here
            date,
            myCalendar.get(Calendar.YEAR),
            myCalendar.get(Calendar.MONTH),
            myCalendar.get(Calendar.DAY_OF_MONTH)
        )

        // Set the maximum and minimum date for the DatePickerDialog
        datePickerDialog.datePicker.maxDate = System.currentTimeMillis()
        datePickerDialog.datePicker.minDate = minimumDate

        datePickerDialog.show()
    }

    private fun updateLabel() {
        val myFormat = "yyyy/dd/MM"
        val formattedDate = myFormat.replace("/", "-")
        val dateFormat = SimpleDateFormat(formattedDate, Locale.US)
        cust_input_dob.setText(dateFormat.format(myCalendar.time))
    }

    private fun updateCustomer() {
        val apiService = RetrofitClient.retrofit.create(MSCreateCustomerApiService::class.java)

        val token = session.token
        val fullName1 = fullName
        val mobile2 = mobile
        val gender1 = gender
        val email1 = email
        val dob1 = dob
        val appointmentDate1 = appointmentDate
        val appointmentTime1 = appointmentTime
        val appointmentDuration1 = appointmentDuration
        val appointmentReason1 = appointmentReason

        try {
            val call = apiService.createCustomer(
                "Bearer $token",
                fullName1,
                mobile2,
                gender1,
                email1,
                dob1,
                appointmentDate1,
                appointmentTime1,
                appointmentDuration1,
                appointmentReason1
            )

            call.enqueue(object : Callback<NormalDataModel> {
                override fun onResponse(call: Call<NormalDataModel>, response: Response<NormalDataModel>) {
                    if (response.isSuccessful) {
                        val customerCreateResponse = response.body()
                        val status = customerCreateResponse?.status
                        val message = customerCreateResponse?.message

                        // Handle the response status and message as needed
                        printLogs("success","onResponse",message)
                        loadingDialog.dismissDialog()
                        loadingDialog.startSucessDialog(message.toString(),this@MSAddCustomers,MSCustomersActivity::class.java)
                    } else {
                        // Handle the error case
                        loadingDialog.dismissDialog()
                        val errorBody = response.errorBody()?.string()
                        if (errorBody != null) {
                            Log.d("Error", errorBody)
                            loadingDialog.showErrorBottomSheetDialog(errorBody)
                        }
                        loadingDialog.showErrorBottomSheetDialog(errorBody + "Error")
                    }
                }

                override fun onFailure(call: Call<NormalDataModel>, t: Throwable) {
                    // Handle the failure case
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

}