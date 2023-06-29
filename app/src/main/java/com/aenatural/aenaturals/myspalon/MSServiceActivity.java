package com.aenatural.aenaturals.myspalon;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.aenatural.aenaturals.R;
import com.aenatural.aenaturals.apiservices.MSListServices;
import com.aenatural.aenaturals.apiservices.datamodels.MSListServiceDM;
import com.aenatural.aenaturals.apiservices.datamodels.Service;
import com.aenatural.aenaturals.baseframework.BaseClass;
import com.aenatural.aenaturals.baseframework.Session;
import com.aenatural.aenaturals.common.DialogPB;
import com.aenatural.aenaturals.common.RetrofitClient;
import com.aenatural.aenaturals.myspalon.Adapter.ServiceListAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MSServiceActivity extends BaseClass {

    Button ms_add_service_btn;
    RecyclerView MSListserviceRV;
    Session session;
    String token;
    DialogPB dialogPB;
    List<Service> servicedata;
    ServiceListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayoutXml();

        session = new Session(this);
        token = session.getToken();
        dialogPB = new DialogPB(this);
        servicedata = new ArrayList<>();

        initializeViews();
        initializeInputs();
        initializeClickListners();
    }

    @Override
    protected void setLayoutXml() {
        setContentView(R.layout.activity_msservice);
        birdTheme();
    }

    @Override
    protected void initializeViews() {
        ms_add_service_btn = findViewById(R.id.ms_add_service_btn);
        MSListserviceRV = findViewById(R.id.MSListserviceRV);

    }

    @Override
    protected void initializeClickListners() {
        ms_add_service_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MSServiceActivity.this, MSAddServiceActivity.class));
            }
        });
    }

    @Override
    protected void initializeInputs() {
        dialogPB.startLoadingDialog();
        hitServiceListApi();
    }

    private void hitServiceListApi() {
        MSListServices apiService = RetrofitClient.INSTANCE.getRetrofit().create(MSListServices.class);

        Call<MSListServiceDM> call = apiService.listServices("Bearer "+token);
        call.enqueue(new Callback<MSListServiceDM>() {
            @Override
            public void onResponse(Call<MSListServiceDM> call, Response<MSListServiceDM> response) {
                dialogPB.dismissDialog();
                if(response.isSuccessful()){
                    logHandler("hitServiceListApi ",response.body().toString());
                    if(response.body().getStatus().equals("true")) {
                        try {
                            servicedata = response.body().getServices();
                            adapter = new ServiceListAdapter(servicedata);
                            MSListserviceRV.setAdapter(adapter);
                            MSListserviceRV.setLayoutManager(new LinearLayoutManager(MSServiceActivity.this));

                        }catch (Exception e){
                            dialogPB.showErrorBottomSheetDialog("Something went wrong!!");
                        }
                    }
                    else{
                        dialogPB.showErrorBottomSheetDialog("Something went wrong!!");
                    }

                }else{
                    dialogPB.showErrorBottomSheetDialog("No Response from server");
                }
            }

            @Override
            public void onFailure(Call<MSListServiceDM> call, Throwable t) {
                dialogPB.dismissDialog();
                dialogPB.showErrorBottomSheetDialog("Something went wrong");
                logHandler("hitServiceListApiFail ",t.getLocalizedMessage());
            }
        });
    }

    @Override
    protected void initializeLabels() {

    }


}