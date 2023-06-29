package com.aenatural.aenaturals.myspalon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.aenatural.aenaturals.R
import com.aenatural.aenaturals.apiservices.MSSaveService
import com.aenatural.aenaturals.apiservices.datamodels.NormalDataModel
import com.aenatural.aenaturals.baseframework.BaseClass
import com.aenatural.aenaturals.baseframework.Session
import com.aenatural.aenaturals.common.DialogPB
import com.aenatural.aenaturals.common.RetrofitClient.retrofit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MSAddServiceActivity : BaseClass() {

    lateinit var serv_name: EditText
    lateinit var serv_cost: EditText
    lateinit var ms_service_save: Button
    lateinit var loadingDialog: DialogPB
    var service_name = ""
    var service_cost = ""
    lateinit var session: Session


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setLayoutXml()
        initializeViews()
        initializeInputs()
        initializeClickListners()
    }

    override fun setLayoutXml() {
        setContentView(R.layout.activity_msadd_service)
        birdTheme()
    }

    override fun initializeViews() {
        serv_name = findViewById(R.id.serv_name)
        serv_cost = findViewById(R.id.serv_cost)
        ms_service_save = findViewById(R.id.ms_service_save)
        loadingDialog = DialogPB(this)
        session = Session(this)
    }

    override fun initializeClickListners() {
        ms_service_save.setOnClickListener {
            initializeInputs()

            hitAddServiceApi()
        }
    }

    private fun hitAddServiceApi() {
        loadingDialog.startLoadingDialog()
        val tokn = session.token
        var apiService = retrofit.create(MSSaveService::class.java)
//        val coroutineScope = CoroutineScope(Dispatchers.Main)
//        coroutineScope.launch {


            var call =
                apiService.addService("Bearer $tokn", service_name, service_cost)

            call.enqueue(object : Callback<NormalDataModel> {
                override fun onResponse(
                    call: Call<NormalDataModel>,
                    response: Response<NormalDataModel>
                ) {
                    loadingDialog.dismissDialog()
                    if (response.isSuccessful) {
                        logHandler("ADDSERVICERES", response.body().toString())
                    }else{
                        loadingDialog.showErrorBottomSheetDialog("No response from server")
                    }
                }

                override fun onFailure(call: Call<NormalDataModel>, t: Throwable) {
                    logHandler("ErrorResponse",t.message.toString())
                    loadingDialog.showErrorBottomSheetDialog("Something went wrong")
                }

            })


    }

    override fun initializeInputs() {
        service_name = serv_name.text.toString()
        service_cost = serv_cost.text.toString()
    }

    override fun initializeLabels() {
    }
}