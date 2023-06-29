package com.aenatural.aenaturals.myspalon

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aenatural.aenaturals.R
import com.aenatural.aenaturals.apiservices.BeauticianProfileApiService
import com.aenatural.aenaturals.apiservices.datamodels.BeauticianProfileResponse
import com.aenatural.aenaturals.apiservices.datamodels.Staff
import com.aenatural.aenaturals.baseframework.BaseClass
import com.aenatural.aenaturals.baseframework.Session
import com.aenatural.aenaturals.common.DialogPB
import com.aenatural.aenaturals.common.RetrofitClient
import com.aenatural.aenaturals.myspalon.Adapter.BeauticianProfileAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MSBeauticians : BaseClass() {
    lateinit var add_beauticians_btn:Button
    lateinit var backAppbar:ImageButton
    lateinit var loadingDialog: DialogPB
    lateinit var session: Session
    lateinit var beauticianRV: RecyclerView
    private val staff1 = ArrayList<Staff>()
    val beauticianProfileAdapter = BeauticianProfileAdapter(staff1)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setLayoutXml()
        initializeViews()
        initializeClickListners()
        loadingDialog.startLoadingDialog()
        getBeauticianDetails()
    }

    override fun setLayoutXml() {
        setContentView(R.layout.activity_msbeauticians)
        birdTheme()
        session = Session(this)
        loadingDialog = DialogPB(this)

    }

    override fun initializeViews() {
        add_beauticians_btn = findViewById(R.id.add_beauticians_btn)
        backAppbar = findViewById(R.id.backAppbar)
        beauticianRV = findViewById(R.id.beauticianRV)
    }

    override fun initializeClickListners() {
        add_beauticians_btn.setOnClickListener {
            startActivity(Intent(this,MSAddBeauticians::class.java))
        }
        backAppbar.setOnClickListener {
            onBackPressed()
        }
    }

    override fun initializeInputs() {
    }

    override fun initializeLabels() {
    }

    private fun getBeauticianDetails() {
        val apiService = RetrofitClient.retrofit.create(BeauticianProfileApiService::class.java)

        val tkn = session.token

        try {
            val call = apiService.getProfile("Bearer $tkn")
            call.enqueue(object : Callback<BeauticianProfileResponse> {
                override fun onResponse(call: Call<BeauticianProfileResponse>, response: Response<BeauticianProfileResponse>) {
                    if (response.isSuccessful) {
                        loadingDialog.dismissDialog()
                        val profileResponse = response.body()
                        val status = profileResponse?.status
                        val staffList = profileResponse?.staff


                        if (staffList != null) {
                            for (staff in staffList) {
                                val fullName = staff.fullName
                                val mobile = staff.mobile

                                // Create a Staff object with the retrieved values
                                val staffItem = Staff("", "", "", "", mobile, "", "", "", "", "", fullName, "")
                                staff1.add(staffItem)
//                                loadingDialog.showErrorBottomSheetDialog(fullName.toString() + mobile.toString())
                            }
                        }

                        beauticianRV.layoutManager = LinearLayoutManager(this@MSBeauticians)
                            beauticianRV.adapter = beauticianProfileAdapter


                    } else {
                        // Handle the error case
                        logHandler("error1 " , response.message())
                        loadingDialog.showErrorBottomSheetDialog(response.message() + response.body())
                    }
                }

                override fun onFailure(call: Call<BeauticianProfileResponse>, t: Throwable) {
                    // Handle the failure case
                    loadingDialog.dismissDialog()
                    logHandler("FailureResponse",
                        t.message.toString() + " \n" + t.localizedMessage + " \n" + t.cause + " \n" + t.stackTraceToString()
                    )
                    logHandler("CallResponse", call.toString())
                    loadingDialog.showErrorBottomSheetDialog(t.message.toString())

                }
            })
        } catch (e: Exception) {
            logHandler("ExceptionResponse", e.message.toString())
            loadingDialog.showErrorBottomSheetDialog(e.message.toString())
        }
    }
}