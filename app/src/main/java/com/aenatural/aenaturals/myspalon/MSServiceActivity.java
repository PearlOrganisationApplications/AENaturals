package com.aenatural.aenaturals.myspalon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.aenatural.aenaturals.R;
import com.aenatural.aenaturals.baseframework.BaseClass;

public class MSServiceActivity extends BaseClass {
Button ms_add_service_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void setLayoutXml() {
        setContentView(R.layout.activity_msservice);
        birdTheme();
    }

    @Override
    protected void initializeViews() {
        ms_add_service_btn = findViewById(R.id.ms_add_service_btn);
    }

    @Override
    protected void initializeClickListners() {
        ms_add_service_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MSServiceActivity.this,MSAddServiceActivity.class));
            }
        });
    }

    @Override
    protected void initializeInputs() {

    }

    @Override
    protected void initializeLabels() {

    }


}