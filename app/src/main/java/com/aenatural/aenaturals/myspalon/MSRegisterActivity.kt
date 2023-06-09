package com.aenatural.aenaturals.myspalon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.aenatural.aenaturals.R
import com.aenatural.aenaturals.apiservices.MSRegisterService
import com.aenatural.aenaturals.apiservices.datamodels.ErrorResponse
import com.aenatural.aenaturals.apiservices.datamodels.RegisterRequest
import com.aenatural.aenaturals.apiservices.datamodels.RegisterResponse
import com.aenatural.aenaturals.baseframework.BaseClass
import com.aenatural.aenaturals.common.DialogPB
import com.aenatural.aenaturals.common.Login
import com.aenatural.aenaturals.common.RetrofitClient
import com.aenatural.aenaturals.common.RetrofitClient.retrofit
import com.aenatural.aenaturals.customers.CustomerDashboard
import com.google.gson.Gson
import kotlinx.coroutines.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Converter

class MSRegisterActivity : BaseClass() {
    lateinit var tv_signup: TextView
    lateinit var MSregistererrorTV: TextView
    lateinit var ms_register_parlor_password: EditText
    lateinit var ms_register_parlor_confirmpassword: EditText
    lateinit var ms_register_email: EditText
    lateinit var ms_register_parlor_name: EditText
    lateinit var ms_register_parlor_address: EditText
    lateinit var ms_register_pb: LinearLayout
    lateinit var loadingDialog: DialogPB
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setLayoutXml()
        initializeViews()
        loadingDialog = DialogPB(this)
        initializeClickListners()
    }

    override fun setLayoutXml() {
        setContentView(R.layout.activity_msregister)
        getLightGreentheme()
    }

    override fun initializeViews() {

        tv_signup = findViewById(R.id.tv_signup)
        MSregistererrorTV = findViewById(R.id.MSregistererrorTV)
        MSregistererrorTV.visibility = View.GONE

        ms_register_parlor_address = findViewById(R.id.ms_register_parlor_address)
        ms_register_parlor_name = findViewById(R.id.ms_register_parlor_name)
        ms_register_email = findViewById(R.id.ms_register_email)
        ms_register_parlor_password = findViewById(R.id.ms_register_parlor_password)
        ms_register_parlor_confirmpassword = findViewById(R.id.ms_register_parlor_confirmpassword)

        ms_register_pb = findViewById(R.id.ms_register_pb)
        ms_register_pb.visibility = View.GONE
    }

    override fun initializeClickListners() {
        tv_signup.setOnClickListener {
            MSregistererrorTV.visibility = View.GONE
            ms_register_pb.visibility = View.VISIBLE
            signUpValidation()
        }
    }

     fun signUpValidation(){
        if(ms_register_parlor_password.text.toString().trim() == ms_register_parlor_confirmpassword.text.toString().trim()){
            registerApi()
        }
        else{
            ms_register_pb.visibility = View.GONE
                     loadingDialog.showErrorBottomSheetDialog("Password must be identical")

        }
    }

    private fun registerApi() {
        var email = ms_register_email.text.toString()
        var name = ms_register_parlor_name.text.toString()
        var address = ms_register_parlor_address.text.toString()
        var password = ms_register_parlor_password.text.toString()

        val coroutineScope = CoroutineScope(Dispatchers.Main)
        coroutineScope.launch {

            try {
                val apiService = retrofit.create(MSRegisterService::class.java)

                try {
                    val response = apiService.registerUser(email, password, name, address)
                    if (response.isSuccessful) {
                        ms_register_pb.visibility = View.GONE
                        val responseData = response.body()
                        logHandler("RegisterResponse", responseData.toString())
                        if (responseData != null) {
                            if (responseData.status == "false")
                                errorHandler(responseData.message, MSregistererrorTV, true)
                            else {
                                withContext(Dispatchers.Main) {
                                    startActivity(
                                        Intent(
                                            this@MSRegisterActivity,
                                            Login::class.java
                                        )
                                    )
                                }
                            }
                        } else {
                            ms_register_pb.visibility = View.VISIBLE
                            errorHandler("No response from server", MSregistererrorTV, true)
                        }
                    } else {
                        val errorResponse = response.errorBody()?.let { errorBody ->
                            val converter: Converter<ResponseBody, ErrorResponse> =
                                retrofit.responseBodyConverter(
                                    ErrorResponse::class.java,
                                    arrayOfNulls<Annotation>(0)
                                )
                            converter.convert(errorBody)
                        }
                        if (errorResponse != null) {
                            logHandler("ErrorResponse", errorResponse.toString())
                        } else {
                            logHandler("Error", response.message())
                        }
                    }
                } catch (e: Exception) {
                    logHandler("error", "exception$e")
                }

            } catch (e: Exception) {
                logHandler("Catch exception ", "" + e.toString());
            }
        }
    }

    override fun initializeInputs() {

    }

    override fun initializeLabels() {

    }


}