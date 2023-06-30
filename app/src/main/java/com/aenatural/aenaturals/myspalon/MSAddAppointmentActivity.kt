package com.aenatural.aenaturals.myspalon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import com.aenatural.aenaturals.R
import com.aenatural.aenaturals.baseframework.BaseClass
import com.aenatural.aenaturals.baseframework.Session
import com.aenatural.aenaturals.common.DialogPB
import com.aenatural.aenaturals.common.RetrofitClient.details
import java.util.*

class MSAddAppointmentActivity : BaseClass() {
    private val myCalendar: Calendar = Calendar.getInstance()
    private lateinit var appointment_input_dob: EditText
    private lateinit var appointment_input_fullname: EditText
    private lateinit var appointment_time: EditText
    private lateinit var appointment_tv_submit: TextView
    private lateinit var session: Session
    private lateinit var loadingDialogPB: DialogPB
    var dobtxt = ""
    var fullNametxt = ""
    var timetxt = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setLayoutXml()
        initializeViews()
        initializeClickListners()
    }

    override fun setLayoutXml() {
        setContentView(R.layout.activity_msadd_appointment)
        birdTheme()
        session = Session(this)
        loadingDialogPB = DialogPB(this)
    }

    override fun initializeViews() {
        appointment_input_dob = findViewById(R.id.appointment_input_dob)
        appointment_time = findViewById(R.id.appointment_time)
        appointment_input_fullname = findViewById(R.id.appointment_input_fullname)
        appointment_tv_submit = findViewById(R.id.appointment_tv_submit)
    }

    override fun initializeClickListners() {
        appointment_input_dob.setOnClickListener {
            showDatePicker(myCalendar,appointment_input_dob)
        }
        appointment_time.setOnClickListener {
            openTimePicker(appointment_time,myCalendar)
        }
        appointment_tv_submit.setOnClickListener {
            dobtxt = appointment_input_dob.text.toString()
            fullNametxt = appointment_input_fullname.text.toString()
            timetxt = appointment_time.text.toString()

            if (dobtxt.isNotEmpty() && timetxt.isNotEmpty() && fullNametxt.isNotEmpty()) {

            }else{
                loadingDialogPB.showErrorBottomSheetDialog(details)
            }
        }
    }


    override fun initializeInputs() {

    }

    override fun initializeLabels() {

    }
}