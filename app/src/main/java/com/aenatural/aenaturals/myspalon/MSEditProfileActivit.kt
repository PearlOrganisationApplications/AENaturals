package com.aenatural.aenaturals.myspalon

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.LinearLayout
import com.aenatural.aenaturals.R
import com.aenatural.aenaturals.apiservices.MSGetProfileApiService
import com.aenatural.aenaturals.apiservices.datamodels.MSProfileResponseDM
import com.aenatural.aenaturals.baseframework.BaseClass
import com.aenatural.aenaturals.common.RetrofitClient.retrofit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.*

class MSEditProfileActivit : BaseClass() {
    lateinit var profile_backIB: ImageButton
    lateinit var ms_profile_pb:LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setLayoutXml()
        initializeViews()
        initializeClickListners()
        getProfileResponse()
    }

    private fun getProfileResponse() {
        var apiService = retrofit.create(MSGetProfileApiService::class.java)
        GlobalScope.launch(Dispatchers.Main) {
        try {
            val call:Call<MSProfileResponseDM> = apiService.getProfile("Bearer ys35KfzRPD2+UPuGRr4Qh7Ow5NnKG4fne4Y3mRmUgXZHhniKhbyWQ6cHDYEqVfdSXO6hCw09mrQUEEmpKG1to1XnnkrY4HAbe0cCW7RhnSBtyi8E")
            call.enqueue(object:Callback<MSProfileResponseDM>{
                override fun onResponse(
                    call: Call<MSProfileResponseDM>,
                    response: Response<MSProfileResponseDM>
                ) {
                    ms_profile_pb.visibility = View.GONE
                    if(response.isSuccessful){
                        val data = response.body()
                        Log.d("ProfileResponse ",data.toString())
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
    }}

    override fun setLayoutXml() {
        setContentView(R.layout.activity_msedit_profile)
        birdTheme()
    }

    override fun initializeViews() {
        profile_backIB = findViewById(R.id.profile_backIB)
        ms_profile_pb = findViewById(R.id.ms_profile_pb)
    }

    override fun initializeClickListners() {
        profile_backIB.setOnClickListener {
            onBackPressed()
        }
    }


    override fun initializeInputs() {
    }

    override fun initializeLabels() {
    }
}