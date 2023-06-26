package com.aenatural.aenaturals.myspalon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import com.aenatural.aenaturals.R
import com.aenatural.aenaturals.apiservices.MSRegisterService
import com.aenatural.aenaturals.apiservices.datamodels.RegisterRequest
import com.aenatural.aenaturals.baseframework.BaseClass
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MSRegisterActivity : BaseClass() {
    lateinit var tv_signup:TextView
    lateinit var ms_register_parlor_password:EditText
    lateinit var ms_register_email:EditText
    lateinit var ms_register_parlor_name:EditText
    lateinit var ms_register_parlor_address:EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setLayoutXml()
        initializeViews()
        initializeClickListners()
    }

    override fun setLayoutXml() {
        setContentView(R.layout.activity_msregister)
        getLightGreentheme()
    }

    override fun initializeViews() {
        tv_signup = findViewById(R.id.tv_signup)

        ms_register_parlor_address = findViewById(R.id.ms_register_parlor_address)
        ms_register_parlor_name = findViewById(R.id.ms_register_parlor_name)
        ms_register_email = findViewById(R.id.ms_register_email)
        ms_register_parlor_password = findViewById(R.id.ms_register_parlor_password)
    }

    override fun initializeClickListners() {
tv_signup.setOnClickListener {
  //  startActivity(Intent(this,MSHomeScreenActivity::class.java))
    registerApi()
}
    }

    private fun registerApi() {
var email = ms_register_email.text.toString()
var name = ms_register_parlor_name.text.toString()
var address = ms_register_parlor_address.text.toString()
var password = ms_register_parlor_password.text.toString()

        GlobalScope.launch(Dispatchers.Main) {
            try {
                val request = RegisterRequest(email, password, name, address)
                val response = MSRegisterService.registerUser(request)
                if (response.isSuccessful) {
                    val registerResponse = response.body()
                    Log.d("RegisterApiResponse",response)
                    // Handle the response
                } else {
                    val errorMessage = response.errorBody()?.string()
                    // Handle the error
                }
            } catch (e: Exception) {
                // Handle exceptions
            }
        }
    }

    override fun initializeInputs() {

    }

    override fun initializeLabels() {

    }
}